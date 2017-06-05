package com.manager.wallet.moneymanager.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.wallet.moneymanager.Fragments.Income;
import com.manager.wallet.moneymanager.R;
import com.manager.wallet.moneymanager.constants.constantUtils.AppConstantsUtils;
import com.manager.wallet.moneymanager.transactions.ExpenseModel;
import com.manager.wallet.moneymanager.transactions.IncomeModel;
import com.manager.wallet.moneymanager.transactions.Remark;
import com.manager.wallet.moneymanager.transactions.Transaction;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by silverlabs on 2/26/17.
 */

public class DetailStatementAdapter<T> extends BaseAdapter
{
    List<ExpenseModel> transactionList;
    List<IncomeModel> incomeList;
    LayoutInflater inflater;
    Remark type;
    public DetailStatementAdapter(Remark type, Context context)
    {
         this.type = type;
        inflater = LayoutInflater.from(context);
        Realm myRealm = Realm.getInstance(context);
        if(type.getRemark().equals(AppConstantsUtils.EXPENSIVETYPE)) {
            RealmResults<ExpenseModel> results3 =
                    myRealm.where(ExpenseModel.class)
                            .findAll();
            transactionList = results3;
        }
        if(type.getRemark().equals(AppConstantsUtils.INCOMETYPE))
        {
            RealmResults<IncomeModel> results3 =
                    myRealm.where(IncomeModel.class)
                            .findAll();
            incomeList = results3;
        }
      //  Log.i("transaction list sie", ""+transactionList.size());
    }

    public DetailStatementAdapter(Remark type, Context context,List<ExpenseModel> expenseModels)
    {
        this.type = type;
        inflater = LayoutInflater.from(context);
        transactionList = expenseModels;
    }
    public DetailStatementAdapter(Remark type, Context context,List<IncomeModel> incomeModels,Integer val)
    {
        this.type = type;
        inflater = LayoutInflater.from(context);
        incomeList = incomeModels;
    }
    @Override
    public int getCount() {
        if(type.getRemark().equals(AppConstantsUtils.EXPENSIVETYPE)) {
            return transactionList.size();
        }
        if(type.getRemark().equals(AppConstantsUtils.INCOMETYPE)) {
            return incomeList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if(type.getRemark().equals(AppConstantsUtils.EXPENSIVETYPE))
        {
            return transactionList.get(i);
        }
        if(type.getRemark().equals(AppConstantsUtils.INCOMETYPE))
        {
            return incomeList.get(i);
        }
       return null;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
             view = inflater.inflate(R.layout.list_transaction_view,null,false);
            holder.dateTView = (TextView) view.findViewById(R.id.date);
            holder.categoryTView = (TextView)view.findViewById(R.id.category);
            holder.amountTView = (TextView)view.findViewById(R.id.amount);
            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();
        if(type.getRemark().equals(AppConstantsUtils.EXPENSIVETYPE)) {
            holder.dateTView.setText(transactionList.get(i).getDate());
            holder.categoryTView.setText(transactionList.get(i).getCategory());
            holder.amountTView.setText("" + transactionList.get(i).getAmount());
            Log.i("Month","Monthexpense "+transactionList.get(i).getMonth());
        }
        if(type.getRemark().equals(AppConstantsUtils.INCOMETYPE)) {
            holder.dateTView.setText(incomeList.get(i).getDate());
            holder.categoryTView.setText(incomeList.get(i).getCategory());
            holder.amountTView.setText("" + incomeList.get(i).getAmount());
            Log.i("Month","Monthincome "+incomeList.get(i).getMonth());
        }
        return view;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    private class ViewHolder
    {
       TextView dateTView;
       TextView categoryTView;
        TextView amountTView;


    }
}
