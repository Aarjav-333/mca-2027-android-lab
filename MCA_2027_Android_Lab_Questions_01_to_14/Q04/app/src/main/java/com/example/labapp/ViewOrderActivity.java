package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.widget.*;
public class ViewOrderActivity extends Activity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_view_order);
  SharedPreferences sp=getSharedPreferences("pizza",0);
  ((TextView)findViewById(R.id.summary)).setText(sp.getString("summary","No pizza selected")+"\nTotal: Rs."+sp.getInt("total",0));
 }
}
