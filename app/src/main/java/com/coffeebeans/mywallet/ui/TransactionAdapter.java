package com.coffeebeans.mywallet.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coffeebeans.mywallet.R;
import com.coffeebeans.mywallet.data.Transaction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<Transaction> walletTransactionList;

    TransactionAdapter(List<Transaction> walletTransactions) {
        this.walletTransactionList = walletTransactions;
    }

    @NonNull
    @Override
    public TransactionAdapter.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_transactions, viewGroup, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.TransactionViewHolder transactionViewHolder, int position) {
        Transaction walletTransaction = walletTransactionList.get(position);

        transactionViewHolder.setTvAmount(walletTransaction.getAmount());
        transactionViewHolder.setTvDate(walletTransaction.getDate());
        transactionViewHolder.setTvDescription(walletTransaction.getDescription());
        transactionViewHolder.setTvType(walletTransaction.getType());
    }

    @Override
    public int getItemCount() {
        if (walletTransactionList.isEmpty()) return 0;
        return walletTransactionList.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_date)
        TextView tvDate;

        TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setTvAmount(String tvAmount) {
            this.tvAmount.setText(tvAmount);
        }

        void setTvDescription(String tvDescription) {
            this.tvDescription.setText(tvDescription);
        }

        void setTvType(String tvType) {
            this.tvType.setText(tvType);
        }

        void setTvDate(String tvDate) {
            this.tvDate.setText(tvDate);
        }
    }
}
