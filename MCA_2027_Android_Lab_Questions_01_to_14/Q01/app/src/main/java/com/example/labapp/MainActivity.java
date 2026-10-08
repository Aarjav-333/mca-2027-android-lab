package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.widget.*;
public class MainActivity extends Activity {
 EditText user,pass;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);
  user=findViewById(R.id.user);pass=findViewById(R.id.pass);
  findViewById(R.id.login).setOnClickListener(v->{
   String u=user.getText().toString().trim(),p=pass.getText().toString();
   if(u.isEmpty()||!p.equals("1234")){Toast.makeText(this,"Invalid login",0).show();return;}
   getSharedPreferences("quiz",0).edit().putString("user",u).apply();
   startActivity(new Intent(this,u.equals("creator")?CreatorActivity.class:StudentActivity.class));finish();
  });
 }
}
