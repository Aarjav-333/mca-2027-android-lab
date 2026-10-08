package com.example.labapp;

import android.app.*;
import android.os.Bundle;
import android.content.*;
import android.net.Uri;
import android.view.*;
import android.widget.*;

public class DashboardActivity extends Activity {
    String[] doctors = {
        "Cardiologist", "Neurologist", "Dermatologist",
        "Orthopedic", "Pediatrician", "ENT Specialist"
    };

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_dashboard);
        TextView welcome = findViewById(R.id.welcome);
        TextView details = findViewById(R.id.details);
        ListView list = findViewById(R.id.list);

        SharedPreferences sp = getSharedPreferences("Patient", MODE_PRIVATE);
        welcome.setText("Welcome, " + sp.getString("name", "Patient"));
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            details.setText("Age: " + extras.getString("age") +
                    "\nToken/Date: " + extras.getString("token"));

        list.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, doctors));
        list.setOnItemClickListener((p, v, pos, id) -> {
            new AlertDialog.Builder(this)
                .setTitle("Appointment")
                .setMessage("Book " + doctors[pos] + "?")
                .setPositiveButton("Confirm", (d, which) ->
                    Toast.makeText(this, "Appointment selected: " + doctors[pos],
                            Toast.LENGTH_SHORT).show())
                .setNegativeButton("Cancel", null).show();
        });
        registerForContextMenu(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, v, info);
        menu.setHeaderTitle("Hospital Navigation");
        menu.add(0, 1, 0, "View Hospital Route");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            Uri url = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=General+Hospital+Thiruvananthapuram");
            startActivity(new Intent(Intent.ACTION_VIEW, url));
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
