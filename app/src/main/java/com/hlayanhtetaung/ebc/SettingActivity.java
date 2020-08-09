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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingActivity extends Activity {

    SharedPreferences sharedPref;

    View fee_view, mmk_125;

    TextView value_35, value_50, value_70, value_90, value_110, value_120, value_125,
            kyat_35, kyat_50, kyat_70, kyat_90, kyat_110, kyat_120, kyat_125, fee_title, fee_value,
            title, value;

    private static long UNIT30, UNIT50, UNIT75, UNIT100, UNIT150, UNIT200;

    private static long FEE;

    private static long RATE35, RATE50, RATE70, RATE90, RATE110, RATE120, RATE125;

    String s_35, s_50, s_70, s_90, s_110, s_120, s_125, m_35, m_50, m_70, m_90, m_110, m_120, m_125, s_fee;

    String inputUnits = "";

    private static final int LIMIT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);

        TextView app_bar_title = findViewById(R.id.app_bar_title);
        app_bar_title.setText(R.string.setting);

        ImageView closeApp = findViewById(R.id.close_app);
        closeApp.setImageResource(R.drawable.ic_baseline_close_24);
        closeApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView profile = findViewById(R.id.edit);
        profile.setImageResource(R.drawable.ic_baseline_account_circle_24);
        profile.setOnClickListener(new View.OnClickListener() {
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

        View mmk_35 = findViewById(R.id.mmk_35);
        View mmk_50 = findViewById(R.id.mmk_50);
        View mmk_70 = findViewById(R.id.mmk_70);
        View mmk_90 = findViewById(R.id.mmk_90);
        View mmk_110 = findViewById(R.id.mmk_110);
        View mmk_120 = findViewById(R.id.mmk_120);
        mmk_125 = findViewById(R.id.mmk_125);
        fee_view = findViewById(R.id.fee);

        value_35 = mmk_35.findViewById(R.id.resultsTitle);
        value_50 = mmk_50.findViewById(R.id.resultsTitle);
        value_70 = mmk_70.findViewById(R.id.resultsTitle);
        value_90 = mmk_90.findViewById(R.id.resultsTitle);
        value_110 = mmk_110.findViewById(R.id.resultsTitle);
        value_120 = mmk_120.findViewById(R.id.resultsTitle);
        value_125 = mmk_125.findViewById(R.id.resultsTitle);

        kyat_35 = mmk_35.findViewById(R.id.resultsValue);
        kyat_50 = mmk_50.findViewById(R.id.resultsValue);
        kyat_70 = mmk_70.findViewById(R.id.resultsValue);
        kyat_90 = mmk_90.findViewById(R.id.resultsValue);
        kyat_110 = mmk_110.findViewById(R.id.resultsValue);
        kyat_120 = mmk_120.findViewById(R.id.resultsValue);
        kyat_125 = mmk_125.findViewById(R.id.resultsValue);

        fee_title = fee_view.findViewById(R.id.resultsTitle);
        fee_value = fee_view.findViewById(R.id.resultsValue);

    }

    @Override
    protected void onStart() {
        super.onStart();

        UNIT30 = sharedPref.getLong("UNIT30", 30);
        UNIT50 = sharedPref.getLong("UNIT50", 50);
        UNIT75 = sharedPref.getLong("UNIT75", 75);
        UNIT100 = sharedPref.getLong("UNIT100", 100);
        UNIT150 = sharedPref.getLong("UNIT150", 150);
        UNIT200 = sharedPref.getLong("UNIT200", 200);

        FEE = sharedPref.getLong("FEE", 500);

        RATE35 = sharedPref.getLong("RATE35", 35);
        RATE50 = sharedPref.getLong("RATE50", 50);
        RATE70 = sharedPref.getLong("RATE70", 70);
        RATE90 = sharedPref.getLong("RATE90", 90);
        RATE110 = sharedPref.getLong("RATE110", 110);
        RATE120 = sharedPref.getLong("RATE120", 120);
        RATE125 = sharedPref.getLong("RATE125", 125);

        s_35 = "1 - " + UNIT30 + " Units";
        s_50 = (UNIT30 + 1) + " - " + UNIT50 + " Units";
        s_70 = (UNIT50 + 1) + " - " + UNIT75 + " Units";
        s_90 = (UNIT75 + 1) + " - " + UNIT100 + " Units";
        s_110 = (UNIT100 + 1) + " - " + UNIT150 + " Units";
        s_120 = (UNIT150 + 1) + " - " + UNIT200 + " Units";
        s_125 = "Above " + (UNIT200 + 1) + " Units";

        m_35 = RATE35 + " MMK";
        m_50 = RATE50 + " MMK";
        m_70 = RATE70 + " MMK";
        m_90 = RATE90 + " MMK";
        m_110 = RATE110 + " MMK";
        m_120 = RATE120 + " MMK";
        m_125 = RATE125 + " MMK";

        s_fee = FEE + " MMK";

        value_35.setText(s_35);
        value_50.setText(s_50);
        value_70.setText(s_70);
        value_90.setText(s_90);
        value_110.setText(s_110);
        value_120.setText(s_120);
        value_125.setText(s_125);

        kyat_35.setText(m_35);
        kyat_50.setText(m_50);
        kyat_70.setText(m_70);
        kyat_90.setText(m_90);
        kyat_110.setText(m_110);
        kyat_120.setText(m_120);
        kyat_125.setText(m_125);

        fee_title.setText(R.string.electric_fee);
        fee_value.setText(s_fee);

        value_35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog("1 to ", UNIT30, "UNIT30");
            }
        });

        value_50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog((UNIT30 + 1) + " to ", UNIT50, "UNIT50");
            }
        });

        value_70.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog((UNIT50 + 1) + " to ", UNIT75, "UNIT75");
            }
        });

        value_90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog((UNIT75 + 1) + " to ", UNIT100, "UNIT100");
            }
        });

        value_110.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog((UNIT100 + 1) + " to ", UNIT150, "UNIT150");
            }
        });

        value_120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog((UNIT150 + 1) + " to ", UNIT200, "UNIT200");
            }
        });

        fee_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(" Electric Fee ", FEE, "FEE");
            }
        });

        kyat_35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(s_35, RATE35, "RATE35");
            }
        });

        kyat_50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(s_50, RATE50, "RATE50");
            }
        });

        kyat_70.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(s_70, RATE70, "RATE70");
            }
        });

        kyat_90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(s_90, RATE90, "RATE90");
            }
        });

        kyat_110.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(s_110, RATE110, "RATE110");
            }
        });

        kyat_120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(s_120, RATE120, "RATE120");
            }
        });

        mmk_125.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(s_125, RATE125, "RATE125");
            }
        });

    }

    void showEditDialog(String titleString, final long valueLong, final String saveValue) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.edit_dialog, null);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setView(dialogView);

        title = dialogView.findViewById(R.id.resultsTitle);
        value = dialogView.findViewById(R.id.resultsValue);

        Button button0 = dialogView.findViewById(R.id.onClickB0);
        Button button1 = dialogView.findViewById(R.id.onClickB1);
        Button button2 = dialogView.findViewById(R.id.onClickB2);
        Button button3 = dialogView.findViewById(R.id.onClickB3);
        Button button4 = dialogView.findViewById(R.id.onClickB4);
        Button button5 = dialogView.findViewById(R.id.onClickB5);
        Button button6 = dialogView.findViewById(R.id.onClickB6);
        Button button7 = dialogView.findViewById(R.id.onClickB7);
        Button button8 = dialogView.findViewById(R.id.onClickB8);
        Button button9 = dialogView.findViewById(R.id.onClickB9);
        Button buttonClear = dialogView.findViewById(R.id.onClickClear);
        Button buttonDelete = dialogView.findViewById(R.id.onClickDelete);

        inputUnits = String.valueOf(valueLong);

        title.setText(titleString);
        value.setText(inputUnits);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputUnits.length() <= LIMIT) {
                    input("0");
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputUnits.length() <= LIMIT) {
                    input("1");
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputUnits.length() <= LIMIT) {
                    input("2");
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputUnits.length() <= LIMIT) {
                    input("3");
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputUnits.length() <= LIMIT) {
                    input("4");
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputUnits.length() <= LIMIT) {
                    input("5");
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputUnits.length() <= LIMIT) {
                    input("6");
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputUnits.length() <= LIMIT) {
                    input("7");
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputUnits.length() <= LIMIT) {
                    input("8");
                }
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputUnits.length() <= LIMIT) {
                    input("9");
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputUnits = String.valueOf(valueLong);
                value.setText(inputUnits);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeLastChar(inputUnits);
            }
        });


        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                long value = Long.parseLong(inputUnits);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putLong(saveValue, value);
                editor.apply();

                dialog.dismiss();
                onStart();
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.create().show();
    }

    void input(String s) {
        if (inputUnits.equals("0")) {
            inputUnits = "";
        }
        inputUnits += s;

        value.setText(inputUnits);
        value.setTextSize(18);

    }

    void removeLastChar(String str) {

        if (!str.isEmpty()) {
            inputUnits = str.substring(0, str.length() - 1);
        }

        if (inputUnits.isEmpty()) {
            value.setText(R.string.t0);
            value.setTextSize(18);
        } else {
            value.setTextSize(18);
            value.setText(inputUnits);
        }
    }

}