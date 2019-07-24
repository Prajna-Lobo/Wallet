package com.coffeebeans.mywallet.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.coffeebeans.mywallet.data.WalletTransaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionViewModel extends ViewModel {
    private MutableLiveData<List<WalletTransaction>> walletTransactionList;

    public TransactionViewModel() {
        this.walletTransactionList = new MutableLiveData<>();
    }

    MutableLiveData<List<WalletTransaction>> getWalletTransactions() {
        return walletTransactionList;
    }

    void loadTransactionLocal() {

        List<WalletTransaction> movies = new ArrayList<>();
        movies.add(new WalletTransaction(1, "24-07-2019", "1,00,000", "description1", "Credit"));
        movies.add(new WalletTransaction(2, "24-07-2019", "100", "description2", "Debit"));
        movies.add(new WalletTransaction(3, "24-07-2019", "200", "description3", "Credit"));
        movies.add(new WalletTransaction(4, "24-07-2019", "400", "description4", "Debit"));
        setMovies(movies);
    }

    private void setMovies(List<WalletTransaction> walletTransactions) {
        walletTransactionList.postValue(walletTransactions);
    }
}
