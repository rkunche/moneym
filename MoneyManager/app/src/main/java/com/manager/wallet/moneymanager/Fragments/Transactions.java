package com.manager.wallet.moneymanager.Fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaBrowserCompat;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.manager.wallet.moneymanager.R;
import com.manager.wallet.moneymanager.constants.constantUtils.AppConstantsUtils;
import com.manager.wallet.moneymanager.transactions.ExpenseModel;
import com.manager.wallet.moneymanager.transactions.IncomeModel;
import com.manager.wallet.moneymanager.transactions.Remark;
import com.manager.wallet.moneymanager.transactions.Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;


public class Transactions  extends Fragment {
    private EditText editText;
    private EditText dateEditText;
    private AutoCompleteTextView autoCompleteTextView;
    private Button buttonExpense;
    private Button buttonIncome;

    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transaction_layout,container,false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.remark_auto_text_view_id);
        autoCompleteTextView.setAdapter(adapter);
        editText = (EditText)view.findViewById(R.id.amount_edit_id);
        dateEditText = (EditText)view.findViewById(R.id.date_edit_id);
        autoCompleteTextView = (AutoCompleteTextView)view.findViewById(R.id.remark_auto_text_view_id);
        dateEditText.setText(getCurrentDate());
        buttonExpense = (Button)view.findViewById(R.id.expansive_button_id);
        buttonIncome = (Button)view.findViewById(R.id.income_button_id);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), datePickerListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                //Create a cancel button and set the title of the dialog.
                datePicker.setCancelable(false);
                datePicker.setTitle("Select the date");
                datePicker.show();
            }
        });
        buttonIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Remark remark = new Remark();
                remark.setRemark(AppConstantsUtils.INCOMETYPE);
                prepareTransaction(remark);

            }
        });
        buttonExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Remark remark = new Remark();
                remark.setRemark(AppConstantsUtils.EXPENSIVETYPE);
                prepareTransaction(remark);
            }
        });
        return view;
    }

    private String getCurrentDate()
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-mm-yyyy");
        System.out.println(cal.getTime());
        String formatted = format1.format(cal.getTime());
        System.out.println(formatted);
        return formatted;

    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is called, below method will be called.
        // The arguments will be working to get the Day of Week to show it in a special TextView for it.

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            dateEditText.setText(month1 + "-" + day1 + "-" + year1);
           // editText.setText(DateFormat.format("EEEE", new Date(selectedYear, selectedMonth, selectedDay - 1)).toString());
        }
    };

    private void prepareTransaction(Remark type)
    {
        String dateTime =   dateEditText.getText().toString();
        String category = autoCompleteTextView.getText().toString();
        String amount = editText.getText().toString();
        String arr[] = dateTime.split("-");
        float amoutValue = Float.parseFloat(amount);
        Log.i("values",""+dateTime+" ___"+category+"___"+amoutValue);
        Realm myRealm = Realm.getInstance(getActivity());
        Log.i("vlaues wirte to"," expensese "+arr.length);
        Log.i("vlaues wirte to"," expensese "+arr[0]);
        if(type.getRemark().equals(AppConstantsUtils.EXPENSIVETYPE))
        {
            String monthVal = AppConstantsUtils.getMap().get(arr[0]);
          myRealm.beginTransaction();
            ExpenseModel transaction = myRealm.createObject(ExpenseModel.class);
            transaction.setDate(dateTime);
            transaction.setAmount(amoutValue);
            transaction.setCategory(category);
            transaction.setMonth(monthVal);

            myRealm.commitTransaction();
            Log.i("vlaues wirte to"," expensese");
        }
        if(type.getRemark().equals(AppConstantsUtils.INCOMETYPE))
        {
            String monthVal = AppConstantsUtils.getMap().get(arr[0]);

            myRealm.beginTransaction();
            IncomeModel transaction = myRealm.createObject(IncomeModel.class);
            transaction.setDate(dateTime);
            transaction.setAmount(amoutValue);
            transaction.setCategory(category);
            transaction.setMonth(monthVal);

            myRealm.commitTransaction();
            Log.i("vlaues wirte to"," incomplete");
        }
        reset();

    }
    private void reset()
    {
         dateEditText.setText("");
      autoCompleteTextView.setText("");
         editText.setText("");
    }

}
