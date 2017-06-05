package com.manager.wallet.moneymanager.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.manager.wallet.moneymanager.Fragments.Expenses;
import com.manager.wallet.moneymanager.Fragments.Income;
import com.manager.wallet.moneymanager.Fragments.Transactions;


public class CustomPagerAdapter extends FragmentPagerAdapter {
    private static final int NUMBER_OF_ITEMS = 3;

    public CustomPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                return new Transactions();
            case 1:
                return new Expenses();
            case 2:

            return new Income();


        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Transactions";
            case 1:
                return "Expenses";
            case 2:
                return "Income";
        }
        return null;
    }


}
