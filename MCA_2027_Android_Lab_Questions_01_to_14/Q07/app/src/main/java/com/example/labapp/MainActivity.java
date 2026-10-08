package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.widget.*;
public class MainActivity extends Activity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);
  EditText u=findViewById(R.id.username),p=findViewById(R.id.password);
  findViewById(R.id.login).setOnClickListener(v->{if(u.getText().toString().trim().isEmpty()||!p.getText().toString().equals("1234")){Toast.makeText(this,"Invalid login",0).show();return;}
   getSharedPreferences("leave",0).edit().putString("user",u.getText().toString().trim()).apply();
   startActivity(new Intent(this,LeaveActivity.class));finish();
  });
 }
}
