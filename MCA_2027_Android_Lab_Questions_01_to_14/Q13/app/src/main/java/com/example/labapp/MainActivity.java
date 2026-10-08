package com.example.labapp;

import android.app.Activity;
import android.os.Bundle;
import android.content.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText name, age, token;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        token = findViewById(R.id.token);

        findViewById(R.id.submit).setOnClickListener(v -> {
            String n = name.getText().toString().trim();
            String a = age.getText().toString().trim();
            String t = token.getText().toString().trim();

            if (n.isEmpty()) { name.setError("Enter Name"); return; }
            if (!a.matches("[0-9]{1,3}") || Integer.parseInt(a) < 1 ||
                    Integer.parseInt(a) > 120) {
                age.setError("Enter valid age"); return;
            }
            if (t.isEmpty()) { token.setError("Enter Token or Date"); return; }

            SharedPreferences sp = getSharedPreferences("Patient", MODE_PRIVATE);
            sp.edit().putString("name", n).apply();

            Bundle extras = new Bundle();
            extras.putString("age", a);
            extras.putString("token", t);
            Intent i = new Intent(this, DashboardActivity.class);
            i.putExtras(extras);
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
            name.setText(""); age.setText(""); token.setText("");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
