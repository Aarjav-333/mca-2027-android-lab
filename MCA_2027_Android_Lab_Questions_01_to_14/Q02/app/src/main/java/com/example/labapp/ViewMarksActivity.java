package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.view.*;import android.widget.*;
import java.util.*;
public class ViewMarksActivity extends Activity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_view_marks);
  SharedPreferences sp=getSharedPreferences("students",0);Spinner spinner=findViewById(R.id.spinner);TextView details=findViewById(R.id.details);
  ArrayList<String> names=new ArrayList<>();int count=sp.getInt("count",0);
  for(int j=0;j<count;j++)names.add(sp.getString("name"+j,""));
  if(count==0){details.setText("No student records");return;}
  spinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,names));
  spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
   public void onNothingSelected(AdapterView<?> p){}
   public void onItemSelected(AdapterView<?> p,View v,int pos,long id){
    details.setText("Name: "+names.get(pos)+"\nInternal: "+sp.getInt("in"+pos,0)+"\nExternal: "+sp.getInt("ex"+pos,0)+"\nTotal: "+(sp.getInt("in"+pos,0)+sp.getInt("ex"+pos,0)));
   }
  });
 }
}
