package com.example.labapp;

import android.app.*;
import android.os.Bundle;
import android.content.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText username, weight;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        weight = findViewById(R.id.weight);

        findViewById(R.id.setup).setOnClickListener(v -> {
            String u = username.getText().toString().trim();
            String w = weight.getText().toString().trim();
            if (u.isEmpty()) { username.setError("Enter Username"); return; }
            if (w.isEmpty()) { weight.setError("Enter Target Weight"); return; }

            new AlertDialog.Builder(this)
                .setTitle("Profile Setup")
                .setMessage("Complete your fitness profile?")
                .setPositiveButton("Confirm", (d, which) -> {
                    SharedPreferences sp = getSharedPreferences("Fitness", MODE_PRIVATE);
                    sp.edit().putString("username", u).apply();
                    Intent i = new Intent(this, DashboardActivity.class);
                    i.putExtra("weight", w);
                    startActivity(i);
                })
                .setNegativeButton("Cancel", null).show();
        });
    }
}
