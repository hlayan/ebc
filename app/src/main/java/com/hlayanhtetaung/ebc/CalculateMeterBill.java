package com.hlayanhtetaung.ebc;

import android.content.Context;
import android.content.SharedPreferences;

public class CalculateMeterBill{

    SharedPreferences sharedPref;

    private static long UNIT30, UNIT50, UNIT75, UNIT100, UNIT150, UNIT200;

    private static long FEE;

    private static long RATE35, RATE50, RATE70, RATE90, RATE110, RATE120, RATE125;

    private static long OVER30, OVER50, OVER75, OVER100, OVER150, OVER200;

    private static long findDifference(long less, long more){
        return more - less;
    }

    public CalculateMeterBill(Context context) {

        sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);

        UNIT30 = sharedPref.getLong("UNIT30",30);
        UNIT50 = sharedPref.getLong("UNIT50",50);
        UNIT75 = sharedPref.getLong("UNIT75",75);
        UNIT100 = sharedPref.getLong("UNIT100",100);
        UNIT150 = sharedPref.getLong("UNIT150",150);
        UNIT200 = sharedPref.getLong("UNIT200",200);

        FEE = sharedPref.getLong("FEE",500);

        RATE35 = sharedPref.getLong("RATE35",35);
        RATE50 = sharedPref.getLong("RATE50",50);
        RATE70 = sharedPref.getLong("RATE70",70);
        RATE90 = sharedPref.getLong("RATE90",90);
        RATE110 = sharedPref.getLong("RATE110",110);
        RATE120 = sharedPref.getLong("RATE120",120);
        RATE125 = sharedPref.getLong("RATE125",125);

        OVER30 = UNIT30 * RATE35;
        OVER50 = (findDifference(UNIT30,UNIT50) * RATE50) + OVER30;
        OVER75 = (findDifference(UNIT50,UNIT75) * RATE70) + OVER50;
        OVER100 = (findDifference(UNIT75,UNIT100) * RATE90) + OVER75;
        OVER150 = (findDifference(UNIT100,UNIT150) * RATE110) + OVER100;
        OVER200 = (findDifference(UNIT150,UNIT200) * RATE120) + OVER150;

    }

    public long calculate(Long units){

        long test,results = 0;

        if (units == 0) {
            return 0;
        }else

        if (units > UNIT200) {
            test = units - UNIT200;
            results = (test * RATE125) + OVER200;
        }else

        if (units > UNIT150) {
            test = units - UNIT150;
            results = (test * RATE120) + OVER150;
        }else

        if (units > UNIT100) {
            test = units - UNIT100;
            results = (test * RATE110) + OVER100;
        }else

        if (units > UNIT75) {
            test = units - UNIT75;
            results = (test * RATE90) + OVER75;
        }else

        if (units > UNIT50) {
            test = units - UNIT50;
            results = (test * RATE70) + OVER50;
        }else

        if (units > UNIT30) {
            test = units - UNIT30;
            results = (test * RATE50) + OVER30;
        }else

        if (units >= 1) {
            results = units * RATE35;
        }

        return results + FEE;
    }
}
