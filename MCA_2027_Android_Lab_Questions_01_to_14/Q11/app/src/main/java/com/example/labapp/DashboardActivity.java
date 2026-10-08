package com.example.labapp;

import android.app.Activity;
import android.os.Bundle;
import android.content.*;
import android.net.Uri;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

public class DashboardActivity extends Activity {
    ArrayList<String> services = new ArrayList<>();

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_dashboard);

        TextView welcome = findViewById(R.id.welcome);
        TextView details = findViewById(R.id.details);
        ListView list = findViewById(R.id.list);

        SharedPreferences sp = getSharedPreferences("Room", MODE_PRIVATE);
        String ac = sp.getString("ac", "OFF");
        int lights = 0;
        if (sp.getBoolean("light1", false)) {
            services.add("Room Light ON"); lights++;
        }
        if (sp.getBoolean("light2", false)) {
            services.add("Bed Light ON"); lights++;
        }
        if (!ac.equals("OFF")) services.add("AC - " + ac);
        if (sp.getBoolean("spa", false)) services.add("Spa Request");
        if (sp.getBoolean("laundry", false)) services.add("Laundry Request");
        if (sp.getBoolean("food", false)) services.add("Food Service");
        if (sp.getBoolean("cleaning", false)) services.add("Room Cleaning");
        if (services.isEmpty()) services.add("No active requests");

        welcome.setText("Welcome, Guest! Climate: " + ac);
        details.setText("AC Mode: " + ac + "\nLights ON: " + lights);

        list.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, services));
        list.setOnItemClickListener((p, v, pos, id) ->
            Toast.makeText(this, services.get(pos) + " active",
                    Toast.LENGTH_SHORT).show());
        registerForContextMenu(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, v, info);
        menu.setHeaderTitle("Hotel Spa");
        menu.add(0, 1, 0, "View Spa Location");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            // Example coordinates for demonstrating map navigation.
            Uri url = Uri.parse("https://www.google.com/maps/search/?api=1&query=8.40396%2C76.97410");
            startActivity(new Intent(Intent.ACTION_VIEW, url));
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
