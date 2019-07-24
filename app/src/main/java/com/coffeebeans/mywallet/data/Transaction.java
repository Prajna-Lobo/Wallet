package com.coffeebeans.mywallet.data;

public class Transaction {
    private long id;
    private String date;
    private String amount;
    private String description;
    private String type;

    public Transaction(long id, String date, String amount, String description, String type) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
