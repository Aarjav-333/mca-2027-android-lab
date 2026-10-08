package com.example.labapp;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.*;

public class MainActivity extends Activity {
    Switch light1, light2;
    RadioGroup acGroup;
    CheckBox spa, laundry, food, cleaning;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        light1 = findViewById(R.id.light1);
        light2 = findViewById(R.id.light2);
        acGroup = findViewById(R.id.acGroup);
        spa = findViewById(R.id.spa);
        laundry = findViewById(R.id.laundry);
        food = findViewById(R.id.food);
        cleaning = findViewById(R.id.cleaning);

        findViewById(R.id.submit).setOnClickListener(v -> {
            int choice = acGroup.getCheckedRadioButtonId();
            if (choice == -1) {
                Toast.makeText(this, "Select AC mode", Toast.LENGTH_SHORT).show();
                return;
            }

            String ac = "OFF";
            if (choice == R.id.acCool) ac = "COOL";
            if (choice == R.id.acHeat) ac = "HEAT";

            SharedPreferences sp = getSharedPreferences("Room", MODE_PRIVATE);
            sp.edit()
                .putBoolean("light1", light1.isChecked())
                .putBoolean("light2", light2.isChecked())
                .putString("ac", ac)
                .putBoolean("spa", spa.isChecked())
                .putBoolean("laundry", laundry.isChecked())
                .putBoolean("food", food.isChecked())
                .putBoolean("cleaning", cleaning.isChecked())
                .apply();

            startActivity(new Intent(this, DashboardActivity.class));
        });
    }
}
