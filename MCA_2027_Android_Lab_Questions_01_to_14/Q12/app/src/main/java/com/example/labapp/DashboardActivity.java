package com.example.labapp;

import android.app.Activity;
import android.os.Bundle;
import android.content.*;
import android.net.Uri;
import android.view.*;
import android.widget.*;

public class DashboardActivity extends Activity {
    String[] trainers = {
        "Arun - Strength Training", "Rahul - Weight Loss",
        "Priya - Yoga", "Anu - Cardio", "Vivek - Bodybuilding"
    };
    String[] emails = {
        "arun@example.com", "rahul@example.com", "priya@example.com",
        "anu@example.com", "vivek@example.com"
    };

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_dashboard);
        TextView welcome = findViewById(R.id.welcome);
        TextView target = findViewById(R.id.target);
        ListView list = findViewById(R.id.list);

        SharedPreferences sp = getSharedPreferences("Fitness", MODE_PRIVATE);
        welcome.setText("Welcome, " + sp.getString("username", "User"));
        target.setText("Target Weight: " + getIntent().getStringExtra("weight") + " kg");

        list.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, trainers));
        list.setOnItemClickListener((p, v, pos, id) ->
                Toast.makeText(this, "Selected: " + trainers[pos],
                        Toast.LENGTH_SHORT).show());
        registerForContextMenu(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, v, info);
        menu.setHeaderTitle("Trainer Options");
        menu.add(0, 1, 0, "Schedule Consultation");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int pos = info.position;
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:" + emails[pos]));
            i.putExtra(Intent.EXTRA_SUBJECT, "Fitness Consultation");
            i.putExtra(Intent.EXTRA_TEXT, "I would like to schedule a consultation.");
            try {
                startActivity(i);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
