package com.coffeebeans.mywallet.di;

import com.coffeebeans.mywallet.TransactionViewModel;
import com.coffeebeans.mywallet.domain.GetUserTransactionsUseCase;
import com.coffeebeans.mywallet.ui.TransactionViewModelFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TransactionModule.class})
public interface ApplicationComponent {

    GetUserTransactionsUseCase getUserTransactionUseCase();

    TransactionViewModel getTransactionViewModel();

    TransactionViewModelFactory getTransactionViewModelFactory();
}
