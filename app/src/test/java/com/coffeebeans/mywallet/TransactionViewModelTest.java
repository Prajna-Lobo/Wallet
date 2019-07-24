package com.coffeebeans.mywallet;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.coffeebeans.mywallet.data.Transaction;
import com.coffeebeans.mywallet.domain.GetUserTransactionsUseCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    GetUserTransactionsUseCase transactionsUseCase;

    TransactionViewModel transactionViewModel = new TransactionViewModel();

    @Before
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        Field field = transactionViewModel.getClass().getDeclaredField("transactionsUseCase");
        field.setAccessible(true);
        field.set(transactionViewModel, transactionsUseCase);
    }

    @Test
    public void shouldBeLoadTransactionIntoLiveData() {
        Transaction transaction = new Transaction(1, "25-07-2019", "100", "Coupon", "CR");
        when(transactionsUseCase.getTransaction()).thenReturn(Collections.singletonList(transaction));
        transactionViewModel.loadTransactionLocal();
        assertEquals(Collections.singletonList(transaction), transactionViewModel.getTransactions().getValue());
        System.out.println(transactionViewModel);
    }

    @Test
    public void shouldBeFilterCorrectTransaction() {
        Transaction transaction1 = new Transaction(1, "25-07-2019", "100", "Coupon1", "CR");
        Transaction transaction2 = new Transaction(1, "25-07-2019", "100", "Coupon2", "CR");
        when(transactionsUseCase.getTransaction()).thenReturn(Arrays.asList(transaction1, transaction2));
        transactionViewModel.loadTransactionLocal();
        transactionViewModel.filterByDescription("Coupon1");
        assertEquals(Collections.singletonList(transaction1), transactionViewModel.getTransactions().getValue());
        System.out.println(transactionViewModel);
    }

    @Test
    public void shouldBeGiveZeroTransactionWhenFilterIncorrect() {
        Transaction transaction1 = new Transaction(1, "25-07-2019", "100", "Coupon1", "CR");
        Transaction transaction2 = new Transaction(1, "25-07-2019", "100", "Coupon2", "CR");
        when(transactionsUseCase.getTransaction()).thenReturn(Arrays.asList(transaction1, transaction2));
        transactionViewModel.loadTransactionLocal();
        transactionViewModel.filterByDescription("Coupon3");
        assertTrue(transactionViewModel.getTransactions().getValue().isEmpty());
        System.out.println(transactionViewModel);
    }


}
