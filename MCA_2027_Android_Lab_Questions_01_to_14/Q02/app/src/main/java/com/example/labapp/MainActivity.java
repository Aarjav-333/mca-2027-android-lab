package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.widget.*;
public class MainActivity extends Activity {
 EditText name,in,ex;SharedPreferences sp;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);
  name=findViewById(R.id.name);in=findViewById(R.id.internal);ex=findViewById(R.id.external);sp=getSharedPreferences("students",0);
  findViewById(R.id.save).setOnClickListener(v->{String n=name.getText().toString().trim(),a=in.getText().toString(),c=ex.getText().toString();
   if(n.isEmpty()||a.isEmpty()||c.isEmpty()){Toast.makeText(this,"Fill all fields",0).show();return;}
   int i,e;try{i=Integer.parseInt(a);e=Integer.parseInt(c);}catch(Exception err){Toast.makeText(this,"Enter numeric marks",0).show();return;}
   if(i<0||i>50||e<0||e>100){Toast.makeText(this,"Marks out of range",0).show();return;}
   int count=sp.getInt("count",0);
   sp.edit().putString("name"+count,n).putInt("in"+count,i).putInt("ex"+count,e).putInt("count",count+1).apply();
   Toast.makeText(this,"Saved",0).show();name.setText("");in.setText("");ex.setText("");
  });
  findViewById(R.id.view).setOnClickListener(v->startActivity(new Intent(this,ViewMarksActivity.class)));
 }
}
