package com.coffeebeans.mywallet.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coffeebeans.mywallet.R;
import com.coffeebeans.mywallet.data.WalletTransaction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_transaction)
    RecyclerView rvTransaction;

    TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvTransaction.setLayoutManager(layoutManager);
        rvTransaction.setItemAnimator(new DefaultItemAnimator());

        TransactionViewModel viewModel = createViewModel();
        viewModel.getWalletTransactions().observe(this, new WalletTransactionObserver());
        viewModel.loadTransactionLocal();
    }

    private TransactionViewModel createViewModel() {
        return ViewModelProviders.of(this).get(TransactionViewModel.class);
    }

    private class WalletTransactionObserver implements Observer<List<WalletTransaction>> {

        @Override
        public void onChanged(@Nullable List<WalletTransaction> walletTransactions) {
            if (walletTransactions == null) return;

            adapter = new TransactionAdapter(walletTransactions);
            rvTransaction.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
