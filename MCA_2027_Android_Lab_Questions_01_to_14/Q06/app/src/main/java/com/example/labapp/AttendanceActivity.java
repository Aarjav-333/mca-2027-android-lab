package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.view.*;import android.widget.*;
import java.util.*;
public class AttendanceActivity extends Activity {
 SharedPreferences sp;ArrayList<String> names=new ArrayList<>();ArrayList<Boolean> present=new ArrayList<>();
 ListView list;TextView report;EditText newStudent;boolean teacher;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_attendance);
  sp=getSharedPreferences("attendance",0);teacher=sp.getString("role","student").equals("teacher");
  list=findViewById(R.id.list);report=findViewById(R.id.report);newStudent=findViewById(R.id.newStudent);
  ((TextView)findViewById(R.id.heading)).setText(teacher?"Instructor Attendance":"Student Attendance");
  if(!sp.contains("count")){sp.edit().putInt("count",3).putString("name0","Anu").putString("name1","Rahul").putString("name2","Arun").apply();}
  reload();
  if(!teacher){newStudent.setVisibility(View.GONE);findViewById(R.id.add).setVisibility(View.GONE);findViewById(R.id.save).setVisibility(View.GONE);}
  findViewById(R.id.add).setOnClickListener(v->{String n=newStudent.getText().toString().trim();if(n.isEmpty()){newStudent.setError("Enter name");return;}
   int count=sp.getInt("count",0);sp.edit().putString("name"+count,n).putInt("count",count+1).apply();newStudent.setText("");reload();});
  findViewById(R.id.save).setOnClickListener(v->{SharedPreferences.Editor e=sp.edit();int sessions=sp.getInt("sessions",0)+1;e.putInt("sessions",sessions);
   for(int j=0;j<names.size();j++)if(present.get(j))e.putInt("att"+j,sp.getInt("att"+j,0)+1);
   e.apply();Toast.makeText(this,"Attendance saved",0).show();showReport();});
 }
 void reload(){names.clear();present.clear();int count=sp.getInt("count",0);for(int j=0;j<count;j++){names.add(sp.getString("name"+j,""));present.add(false);}
  list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names){
   @Override public View getView(int pos,View convert,android.view.ViewGroup parent){
    if(!teacher){TextView t=new TextView(AttendanceActivity.this);t.setPadding(15,20,15,20);int sessions=sp.getInt("sessions",0);
     int pct=sessions==0?0:sp.getInt("att"+pos,0)*100/sessions;t.setText(names.get(pos)+" - "+pct+"% present");return t;}
    Switch sw=new Switch(AttendanceActivity.this);sw.setPadding(15,15,15,15);sw.setText(names.get(pos)+" - Present");sw.setChecked(present.get(pos));
    sw.setOnCheckedChangeListener((button,checked)->present.set(pos,checked));return sw;
   }
  });showReport();
 }
 void showReport(){int sessions=sp.getInt("sessions",0);report.setText("Sessions recorded: "+sessions);}
 @Override public boolean onCreateOptionsMenu(Menu menu){if(teacher)menu.add(0,1,0,"Generate Absentees Report");return true;}
 @Override public boolean onOptionsItemSelected(MenuItem item){if(item.getItemId()==1){StringBuilder s=new StringBuilder("Absent today:\n");for(int j=0;j<names.size();j++)if(!present.get(j))s.append(names.get(j)).append("\n");report.setText(s.toString());return true;}return super.onOptionsItemSelected(item);}
}
