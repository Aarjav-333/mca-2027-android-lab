package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.widget.*;
public class CreatorActivity extends Activity {
 EditText q,a,b,c,d,ans;SharedPreferences sp;
 protected void onCreate(Bundle state){super.onCreate(state);setContentView(R.layout.activity_creator);
  sp=getSharedPreferences("quiz",0);
  if(!sp.getString("user","").equals("creator")){finish();return;}
  q=findViewById(R.id.q);a=findViewById(R.id.a);b=findViewById(R.id.b);c=findViewById(R.id.c);d=findViewById(R.id.d);ans=findViewById(R.id.ans);
  findViewById(R.id.save).setOnClickListener(v->{
   EditText[] fields={q,a,b,c,d,ans};
   for(EditText e:fields)if(e.getText().toString().trim().isEmpty()){e.setError("Required");return;}
   int correct;
   try{correct=Integer.parseInt(ans.getText().toString());}catch(Exception ex){ans.setError("Enter 1-4");return;}
   if(correct<1||correct>4){ans.setError("Enter 1-4");return;}
   int n=sp.getInt("count",0);
   sp.edit().putString("q"+n,q.getText().toString())
    .putString("a"+n,a.getText().toString()).putString("b"+n,b.getText().toString())
    .putString("c"+n,c.getText().toString()).putString("d"+n,d.getText().toString())
    .putInt("ans"+n,correct).putInt("count",n+1).apply();
   for(EditText e:fields)e.setText("");Toast.makeText(this,"Question added",0).show();
  });
  findViewById(R.id.logout).setOnClickListener(v->{sp.edit().remove("user").apply();startActivity(new Intent(this,MainActivity.class));finish();});
 }
}
