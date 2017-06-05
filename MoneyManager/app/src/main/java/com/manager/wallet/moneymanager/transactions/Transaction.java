package com.manager.wallet.moneymanager.transactions;


import io.realm.RealmObject;

public class Transaction extends RealmObject {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    private String date;
    private String category;
    private float amount;


}
