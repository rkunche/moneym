package com.manager.wallet.moneymanager;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by silverlabs on 2/26/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("hi", "i am here");
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(config);
    }
}
