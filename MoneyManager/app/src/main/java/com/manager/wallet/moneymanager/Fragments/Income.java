package com.manager.wallet.moneymanager.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.wallet.moneymanager.Adapters.DetailStatementAdapter;
import com.manager.wallet.moneymanager.R;
import com.manager.wallet.moneymanager.constants.constantUtils.AppConstantsUtils;
import com.manager.wallet.moneymanager.transactions.ExpenseModel;
import com.manager.wallet.moneymanager.transactions.IncomeModel;
import com.manager.wallet.moneymanager.transactions.Remark;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class Income extends Fragment {
    Calendar cal;
    TextView dateTVew;
    Remark remark;
    Realm realm;
    ListView listView;
    TextView expenseTvId;
    TextView incomeTvId;
    TextView balanceTvId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();
        final RealmResults<ExpenseModel> results = realm.where(ExpenseModel.class).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // remove single match
                // results.clear();
            }
        });
        cal = Calendar.getInstance();
        remark = new Remark();
        remark.setRemark(AppConstantsUtils.INCOMETYPE);

        View view = inflater.inflate(R.layout.fragment_expnese_layout,container,false);
        listView = (ListView) view.findViewById(R.id.detail_statement_listV_id);
        DetailStatementAdapter dAdapter = new DetailStatementAdapter(remark,getActivity());
        ImageView left = (ImageView) view.findViewById(R.id.left_id);
        ImageView right = (ImageView) view.findViewById(R.id.right_id);
        dateTVew = (TextView)view.findViewById(R.id.date);
        expenseTvId = (TextView)view.findViewById(R.id.expenses_id);
        incomeTvId = (TextView)view.findViewById(R.id.income_id);
        balanceTvId = (TextView)view.findViewById(R.id.balance_id);

        String date = getCurrentDate();
        String arr[] = date.split(":");
        addAdapter(arr);
        dateTVew.setText(date);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = getPrevDate();
                String arr[] = date.split(":");
                dateTVew.setText(date);
                addAdapter(arr);
                getTotalTransactionCurrentMonth(arr[0]);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = getNextDate();
                String arr[] = date.split(":");
                dateTVew.setText(date);
                addAdapter(arr);
                getTotalTransactionCurrentMonth(arr[0]);
            }
        });
        listView.setAdapter(dAdapter);
        getTotalTransactionCurrentMonth(arr[0]);
        return view;
    }
    private void addAdapter(String arr[])
    {
        List<IncomeModel> expenseModels;
        Log.i("Month","Month arrys "+arr[0]);
        RealmResults<IncomeModel> users = realm.where(IncomeModel.class).equalTo("month", arr[0]).findAll();
        expenseModels = users;
        DetailStatementAdapter dAdapter = new DetailStatementAdapter(remark,getActivity(),expenseModels,1);
        listView.setAdapter(dAdapter);
    }
    private String getNextDate()
    {

        cal.add(Calendar.MONTH,1);
        String date = ""+new SimpleDateFormat("MMM:yyyy").format(cal.getTime());
        return date;
    }

    private String getPrevDate()
    {

        cal.add(Calendar.MONTH,-1);
        String date = ""+new SimpleDateFormat("MMM:yyyy").format(cal.getTime());
        return date;
    }

    private void getTotalTransactionCurrentMonth(String monthNo)
    {
        Log.i("Method called", "method called "+monthNo);
        RealmResults<ExpenseModel> users = realm.where(ExpenseModel.class).equalTo("month", monthNo).findAll();
        List<ExpenseModel> expenseModels = users;

        float totalExpensesForThisMonth = 0;
        float totalIncomeForThisMonth = 0;
        float balance = 0;
        for(ExpenseModel expenseModel : expenseModels)
        {
            totalExpensesForThisMonth = totalExpensesForThisMonth + expenseModel.getAmount();
        }

        RealmResults<IncomeModel> incomeModels = realm.where(IncomeModel.class).equalTo("month", monthNo).findAll();
        List<IncomeModel> incomeModelList = incomeModels;

        for(IncomeModel incomeModel : incomeModelList)
        {
            totalIncomeForThisMonth = totalIncomeForThisMonth + incomeModel.getAmount();
        }
        balance = totalIncomeForThisMonth - totalExpensesForThisMonth;
        Log.i("Method called", "method totalExpensesForThisMonth "+totalExpensesForThisMonth);
        Log.i("Method called", "method totalIncomeForThisMonth "+totalIncomeForThisMonth);
        Log.i("Method called", "method balance "+balance);
        incomeTvId.setText(""+totalIncomeForThisMonth);
        expenseTvId.setText(""+totalExpensesForThisMonth);
        balanceTvId.setText(""+balance);
    }
    private String getCurrentDate()
    {
        String date = ""+new SimpleDateFormat("MMM:yyyy").format(cal.getTime());
        return date;
    }
}
