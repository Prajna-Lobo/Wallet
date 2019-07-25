package com.coffeebeans.mywallet.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.coffeebeans.mywallet.TransactionViewModel;

import javax.inject.Inject;

public class TransactionViewModelFactory implements ViewModelProvider.Factory {

    private TransactionViewModel transactionViewModel;

    @Inject
    public TransactionViewModelFactory(TransactionViewModel transactionViewModel) {
        this.transactionViewModel = transactionViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ViewModel viewModel;
        if (modelClass == TransactionViewModel.class) {
            viewModel = transactionViewModel;
        }
        else {
            throw new RuntimeException("unsupported view model class: " + modelClass);
        }

        return (T) viewModel;
    }
}
