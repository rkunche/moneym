package com.manager.wallet.moneymanager.transactions;


import io.realm.RealmObject;

public class Remark extends RealmObject{
    private String remark;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String type;
}
