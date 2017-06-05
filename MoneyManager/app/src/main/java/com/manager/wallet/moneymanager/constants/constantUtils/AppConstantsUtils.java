package com.manager.wallet.moneymanager.constants.constantUtils;


import java.util.HashMap;
import java.util.Map;

public class AppConstantsUtils {

    public static final String EXPENSIVETYPE = "expense";
    public static final String INCOMETYPE = "income";
    static Map<String,String> monthMap = new HashMap<>();


    public static Map<String,String> getMap()
    {
        monthMap.put("1","Jan");
        monthMap.put("2","Feb");
        monthMap.put("3","Mar");
        monthMap.put("4","Apr");
        monthMap.put("5","May");
        monthMap.put("6","Jun");
        monthMap.put("7","Jul");
        monthMap.put("8","Aug");
        monthMap.put("9","Sep");
        monthMap.put("10","Oct");
        monthMap.put("11","Nov");
        monthMap.put("12","Dec");
      return monthMap;
    }
}
