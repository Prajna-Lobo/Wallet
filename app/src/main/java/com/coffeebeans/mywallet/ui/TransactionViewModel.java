package com.coffeebeans.mywallet.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.coffeebeans.mywallet.data.WalletTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionViewModel extends ViewModel {
    private MutableLiveData<List<WalletTransaction>> data;
    private List<WalletTransaction> walletTransactions;

    public TransactionViewModel() {
        this.data = new MutableLiveData<>();
    }

    MutableLiveData<List<WalletTransaction>> getWalletTransactions() {
        return data;
    }

    void loadTransactionLocal() {

        walletTransactions = new ArrayList<>();
        walletTransactions.add(new WalletTransaction(1, "24-07-2019", "1,00,000", "description1", "Credit"));
        walletTransactions.add(new WalletTransaction(2, "24-07-2019", "100", "description2", "Debit"));
        walletTransactions.add(new WalletTransaction(3, "24-07-2019", "200", "description3", "Credit"));
        walletTransactions.add(new WalletTransaction(4, "24-07-2019", "400", "description4", "Debit"));
        data.postValue(walletTransactions);
    }

    void filterByDescription(String filterText) {


        List<WalletTransaction> filteredTransaction = walletTransactions.stream()
                .filter(walletTransaction -> walletTransaction.getDescription().toLowerCase().contains(filterText.toLowerCase()))
                .collect(Collectors.toList());

        data.postValue(filteredTransaction);
    }
}
