package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;
public class DashboardActivity extends Activity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_dashboard);
  if(!getSharedPreferences("pizza",0).getBoolean("logged",false)){finish();return;}
  findViewById(R.id.customize).setOnClickListener(v->startActivity(new Intent(this,CustomizeActivity.class)));
  findViewById(R.id.order).setOnClickListener(v->startActivity(new Intent(this,ViewOrderActivity.class)));
  findViewById(R.id.logout).setOnClickListener(v->{getSharedPreferences("pizza",0).edit().remove("logged").apply();startActivity(new Intent(this,MainActivity.class));finish();});
 }
}
