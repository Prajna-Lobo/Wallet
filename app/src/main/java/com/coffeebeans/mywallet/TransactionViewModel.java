package com.coffeebeans.mywallet;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.coffeebeans.mywallet.data.Transaction;
import com.coffeebeans.mywallet.domain.GetUserTransactionsUseCase;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class TransactionViewModel extends ViewModel {

    private GetUserTransactionsUseCase transactionsUseCase;
    private MutableLiveData<List<Transaction>> data;
    private List<Transaction> transactions;

    @Inject
    public TransactionViewModel(GetUserTransactionsUseCase transactionsUseCase) {
        this.transactionsUseCase = transactionsUseCase;
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
