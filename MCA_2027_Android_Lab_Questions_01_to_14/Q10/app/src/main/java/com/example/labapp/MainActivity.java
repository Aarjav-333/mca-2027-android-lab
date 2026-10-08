package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;
public class MainActivity extends Activity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);
  findViewById(R.id.student).setOnClickListener(v->open("student"));
  findViewById(R.id.officer).setOnClickListener(v->open("officer"));
 }
 void open(String role){getSharedPreferences("placement",0).edit().putString("role",role).apply();startActivity(new Intent(this,DrivesActivity.class));}
}
