package com.coffeebeans.mywallet.ui;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.coffeebeans.mywallet.R;
import com.coffeebeans.mywallet.TransactionViewModel;
import com.coffeebeans.mywallet.data.Transaction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_transaction)
    RecyclerView rvTransaction;

    private SearchView searchView;

    private TransactionViewModel viewModel;

    TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.toolbar_title);

        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvTransaction.setLayoutManager(layoutManager);
        rvTransaction.setItemAnimator(new DefaultItemAnimator());

        viewModel = createViewModel();
        viewModel.getTransactions().observe(this, new WalletTransactionObserver());
        viewModel.loadTransactionLocal();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.filterByDescription(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                viewModel.filterByDescription(query);
                return true;
            }

        });

        searchView.setOnCloseListener(() -> {
            viewModel.loadTransactionLocal();
            return true;
        });

        return true;
    }

    private TransactionViewModel createViewModel() {
        return ViewModelProviders.of(this).get(TransactionViewModel.class);
    }

    private class WalletTransactionObserver implements Observer<List<Transaction>> {

        @Override
        public void onChanged(@Nullable List<Transaction> walletTransactions) {
            if (walletTransactions == null) return;

            adapter = new TransactionAdapter(walletTransactions);
            rvTransaction.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
