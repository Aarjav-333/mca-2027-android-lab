package com.example.labapp;

import android.app.Activity;
import android.os.Bundle;
import android.content.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText name, license, mobile;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        license = findViewById(R.id.license);
        mobile = findViewById(R.id.mobile);

        findViewById(R.id.submit).setOnClickListener(v -> {
            String n = name.getText().toString().trim();
            String l = license.getText().toString().trim();
            String m = mobile.getText().toString().trim();

            if (n.isEmpty()) { name.setError("Enter Name"); return; }
            if (l.length() < 8) { license.setError("Invalid License Number"); return; }
            if (!m.matches("[0-9]{10}")) {
                mobile.setError("Enter 10 digit number"); return;
            }

            SharedPreferences sp = getSharedPreferences("Driver", MODE_PRIVATE);
            sp.edit().putString("name", n).apply();

            Bundle bundle = new Bundle();
            bundle.putString("license", l);
            bundle.putString("mobile", m);
            Intent i = new Intent(this, DashboardActivity.class);
            i.putExtras(bundle);
            startActivity(i);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Clear Form");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            name.setText(""); license.setText(""); mobile.setText("");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
