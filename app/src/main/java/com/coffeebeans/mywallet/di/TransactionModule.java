package com.coffeebeans.mywallet.di;

import com.coffeebeans.mywallet.TransactionViewModel;
import com.coffeebeans.mywallet.domain.GetUserTransactionsUseCase;
import com.coffeebeans.mywallet.ui.TransactionViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TransactionModule {

    @Provides
    @Singleton
    GetUserTransactionsUseCase providesGetUserTransactionsUseCase() {
        return new GetUserTransactionsUseCase();
    }

    @Provides
    TransactionViewModel providesTransactionViewModel(GetUserTransactionsUseCase getUserTransactionsUseCase) {
        return new TransactionViewModel(getUserTransactionsUseCase);
    }

    @Provides
    TransactionViewModelFactory ProvidesTransactionViewModelFactory(TransactionViewModel transactionViewModel) {
        return new TransactionViewModelFactory(transactionViewModel);
    }
}
