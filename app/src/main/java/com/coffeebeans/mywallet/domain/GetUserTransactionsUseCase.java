package com.coffeebeans.mywallet.domain;

import com.coffeebeans.mywallet.data.Transaction;

import java.util.ArrayList;
import java.util.List;

public class GetUserTransactionsUseCase {

    public GetUserTransactionsUseCase() {
    }

    public List<Transaction> getTransaction() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, "24-07-2019", "1,00,000", "description1", "Credit"));
        transactions.add(new Transaction(2, "24-07-2019", "100", "description2", "Debit"));
        transactions.add(new Transaction(3, "24-07-2019", "200", "description3", "Credit"));
        transactions.add(new Transaction(4, "24-07-2019", "400", "description4", "Debit"));
        return transactions;
    }

}
