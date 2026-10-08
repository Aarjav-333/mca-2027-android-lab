package com.example.labapp;

import android.app.*;
import android.os.Bundle;
import android.content.*;
import android.net.Uri;
import android.view.*;
import android.widget.*;

public class DashboardActivity extends Activity {
    String[] vehicles = {
        "Hatchback Car", "Sedan Car", "SUV Car",
        "Sports Bike", "Scooter", "Cruiser Bike"
    };

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_dashboard);
        TextView welcome = findViewById(R.id.welcome);
        TextView details = findViewById(R.id.details);
        ListView list = findViewById(R.id.list);

        SharedPreferences sp = getSharedPreferences("Driver", MODE_PRIVATE);
        welcome.setText("Welcome, " + sp.getString("name", "Driver"));
        Bundle data = getIntent().getExtras();
        if (data != null)
            details.setText("License: " + data.getString("license") +
                    "\nMobile: " + data.getString("mobile"));

        list.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, vehicles));
        list.setOnItemClickListener((p, v, pos, id) -> {
            new AlertDialog.Builder(this)
                .setTitle("Vehicle Rental")
                .setMessage("Rent " + vehicles[pos] + "?")
                .setPositiveButton("Confirm", (d, which) ->
                    Toast.makeText(this, vehicles[pos] + " selected",
                            Toast.LENGTH_SHORT).show())
                .setNegativeButton("Cancel", null).show();
        });
        registerForContextMenu(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, v, info);
        menu.setHeaderTitle("Rental Information");
        menu.add(0, 1, 0, "Terms of Service");
        menu.add(0, 2, 0, "Insurance Rules");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String url;
        if (item.getItemId() == 1) {
            url = "https://www.google.com/search?q=vehicle+rental+terms+of+service";
        } else if (item.getItemId() == 2) {
            url = "https://www.google.com/search?q=motor+insurance+policy+rules+India";
        } else {
            return super.onContextItemSelected(item);
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        return true;
    }
}
