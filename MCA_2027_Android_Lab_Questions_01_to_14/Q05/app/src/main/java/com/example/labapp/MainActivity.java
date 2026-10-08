package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.view.*;import android.widget.*;
public class MainActivity extends Activity {
 Spinner students;EditText m1,m2,m3;TableLayout report;SharedPreferences sp;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);
  students=findViewById(R.id.students);m1=findViewById(R.id.subject1);m2=findViewById(R.id.subject2);m3=findViewById(R.id.subject3);report=findViewById(R.id.report);sp=getSharedPreferences("marks",0);
  String[] ids={"STU101","STU102","STU103"};students.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,ids));
  students.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
   public void onNothingSelected(AdapterView<?> parent){}
   public void onItemSelected(AdapterView<?> parent,View view,int position,long id){
    String key=ids[position];report.removeAllViews();
    if(sp.contains("m1_"+key)){
     int x=sp.getInt("m1_"+key,0),y=sp.getInt("m2_"+key,0),z=sp.getInt("m3_"+key,0);
     int t=x+y+z;double pct=t/3.0;
     addRow("Student ID",key);addRow("Subject 1",""+x);addRow("Subject 2",""+y);addRow("Subject 3",""+z);
     addRow("Total",""+t+"/300");addRow("Percentage",String.format(java.util.Locale.US,"%.2f%%",pct));addRow("Grade",sp.getString("grade_"+key,""));
    }
   }
  });
 }
 @Override public boolean onCreateOptionsMenu(Menu menu){menu.add(0,1,0,"Compile Report");return true;}
 @Override public boolean onOptionsItemSelected(MenuItem item){if(item.getItemId()==1){compile();return true;}return super.onOptionsItemSelected(item);}
 void addRow(String label,String value){TableRow row=new TableRow(this);TextView a=new TextView(this),b=new TextView(this);
  a.setText(label);b.setText(value);a.setPadding(8,8,8,8);b.setPadding(8,8,8,8);row.addView(a);row.addView(b);report.addView(row);}
 void compile(){EditText[] fields={m1,m2,m3};int[] marks=new int[3];
  for(int j=0;j<3;j++){String s=fields[j].getText().toString();if(s.isEmpty()){fields[j].setError("Required");return;}
   try{marks[j]=Integer.parseInt(s);}catch(Exception ex){fields[j].setError("Invalid");return;}
   if(marks[j]<0||marks[j]>100){fields[j].setError("Enter 0-100");return;}}
  int total=marks[0]+marks[1]+marks[2];double percentage=total/3.0;
  String grade=percentage>=90?"A+":percentage>=75?"A":percentage>=60?"B":percentage>=40?"C":"F";
  String id=students.getSelectedItem().toString();report.removeAllViews();
  addRow("Student ID",id);for(int j=0;j<3;j++)addRow("Subject "+(j+1),""+marks[j]);
  addRow("Total",""+total+" / 300");addRow("Percentage",String.format(java.util.Locale.US,"%.2f%%",percentage));addRow("Grade",grade);
  sp.edit().putInt("m1_"+id,marks[0]).putInt("m2_"+id,marks[1]).putInt("m3_"+id,marks[2])
    .putString("grade_"+id,grade).putString("report_"+id,total+"/300, "+percentage+"%, Grade "+grade).apply();
  Toast.makeText(this,"Report stored locally",0).show();
 }
}
