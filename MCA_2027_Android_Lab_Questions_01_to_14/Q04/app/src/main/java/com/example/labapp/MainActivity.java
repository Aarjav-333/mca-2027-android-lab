package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.widget.*;
public class MainActivity extends Activity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);
  EditText u=findViewById(R.id.user),p=findViewById(R.id.password);
  findViewById(R.id.login).setOnClickListener(v->{if(!u.getText().toString().trim().isEmpty()&&p.getText().toString().equals("1234")){
    getSharedPreferences("pizza",0).edit().putBoolean("logged",true).apply();startActivity(new Intent(this,DashboardActivity.class));finish();
   }else Toast.makeText(this,"Invalid login",0).show();});
 }
}
