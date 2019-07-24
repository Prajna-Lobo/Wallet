package com.coffeebeans.mywallet;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.coffeebeans.mywallet.data.Transaction;
import com.coffeebeans.mywallet.domain.GetUserTransactionsUseCase;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionViewModel extends ViewModel {

    private GetUserTransactionsUseCase transactionsUseCase;
    private MutableLiveData<List<Transaction>> data;
    private List<Transaction> transactions;

    public TransactionViewModel() {
        this.transactionsUseCase = new GetUserTransactionsUseCase();
        this.data = new MutableLiveData<>();
    }

    public MutableLiveData<List<Transaction>> getTransactions() {
        return data;
    }

    public void loadTransactionLocal() {
        transactions = transactionsUseCase.getTransaction();
        data.postValue(transactions);
    }

    public void filterByDescription(String filterText) {


        List<Transaction> filteredTransaction = transactions.stream()
                .filter(walletTransaction -> walletTransaction.getDescription().toLowerCase().contains(filterText.toLowerCase()))
                .collect(Collectors.toList());

        data.postValue(filteredTransaction);
    }
}
