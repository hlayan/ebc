package com.hlayanhtetaung.ebc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView unitsValues, resultsValue;
    String inputUnits;
    private static final int LIMIT = 10;

    SharedPreferences sharedPref;
    CalculateMeterBill calculateMeterBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);

        ImageView closeApp = findViewById(R.id.close_app);
        ImageView setting = findViewById(R.id.edit);

        View units = findViewById(R.id.units);
        View results = findViewById(R.id.results);

        TextView resultsTitle = results.findViewById(R.id.resultsTitle);
        resultsTitle.setText(R.string.results);
        resultsTitle.setTextSize(18);

        TextView unitTitle = units.findViewById(R.id.resultsTitle);
        unitTitle.setTextSize(18);

        unitsValues = units.findViewById(R.id.resultsValue);
        resultsValue = results.findViewById(R.id.resultsValue);
        resultsValue.setText(R.string.resultsValue);
        resultsValue.setTextSize(18);

        inputUnits = "";

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        closeApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        View developer = findViewById(R.id.developer);
        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PackageManager packageManager = getPackageManager();
                    ApplicationInfo appInfo = packageManager.getApplicationInfo("com.facebook.katana", 0);
                    if (appInfo.enabled) {
                        Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/100016026428007"));
                        startActivity(i1);
                    } else {
                        Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/HlayanHtetAung"));
                        startActivity(i2);
                    }
                } catch (Exception e) {
                    Intent i3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/HlayanHtetAung"));
                    startActivity(i3);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        calculateMeterBill = new CalculateMeterBill(this);
    }

    public void onClickB1(View view) {
        if (inputUnits.length() <= LIMIT) {
            input("1");
        }
    }

    public void onClickB2(View view) {
        if (inputUnits.length() <= LIMIT) {
            input("2");
        }
    }

    public void onClickB3(View view) {
        if (inputUnits.length() <= LIMIT) {
            input("3");
        }
    }

    public void onClickB4(View view) {
        if (inputUnits.length() <= LIMIT) {
            input("4");
        }
    }

    public void onClickB5(View view) {
        if (inputUnits.length() <= LIMIT) {
            input("5");
        }
    }

    public void onClickB6(View view) {
        if (inputUnits.length() <= LIMIT) {
            input("6");
        }
    }

    public void onClickB7(View view) {
        if (inputUnits.length() <= LIMIT) {
            input("7");
        }
    }

    public void onClickB8(View view) {
        if (inputUnits.length() <= LIMIT) {
            input("8");
        }
    }

    public void onClickB9(View view) {
        if (inputUnits.length() <= LIMIT) {
            input("9");
        }
    }

    public void onClickB0(View view) {
        if (inputUnits.length() <= LIMIT) {
            input("0");
        }
    }

    public void onClickBClear(View view) {
        inputUnits = "";
        unitsValues.setText(R.string.unitsValue);
        resultsValue.setText(R.string.resultsValue);
    }

    public void onClickBDelete(View view) {
        removeLastChar(inputUnits);
    }

    void input(String s) {
        if (inputUnits.equals("0")) {
            inputUnits = "";
        }
        inputUnits += s;

        String resultUnit = inputUnits + " Units";
        unitsValues.setText(resultUnit);
        unitsValues.setTextSize(18);

        long value = Long.parseLong(inputUnits);
        resultUnit = calculateMeterBill.calculate(value) + " MMK";
        resultsValue.setText(resultUnit);
        resultsValue.setTextSize(18);
    }

    void removeLastChar(String string) {

        if (!string.isEmpty()) {
            inputUnits = string.substring(0, string.length() - 1);
        }

        if (inputUnits.isEmpty()) {
            unitsValues.setText(R.string.unitsValue);
            resultsValue.setText(R.string.resultsValue);
            resultsValue.setTextSize(18);
            unitsValues.setTextSize(18);
        } else {

            String result = inputUnits + " Units";
            unitsValues.setText(result);
            unitsValues.setTextSize(18);
            long value = Long.parseLong(inputUnits);

            result = calculateMeterBill.calculate(value) + " MMK";
            resultsValue.setText(result);
            resultsValue.setTextSize(18);
        }
    }
}