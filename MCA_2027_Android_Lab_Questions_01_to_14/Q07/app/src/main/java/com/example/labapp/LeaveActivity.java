package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.view.*;import android.widget.*;
import java.util.*;
public class LeaveActivity extends Activity {
 SharedPreferences sp;TextView dateText,status;Spinner type;Button date,apply;String chosen="";String user;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_leave);
  sp=getSharedPreferences("leave",0);user=sp.getString("user","");if(user.isEmpty()){finish();return;}
  dateText=findViewById(R.id.dateText);status=findViewById(R.id.status);date=findViewById(R.id.date);apply=findViewById(R.id.apply);type=findViewById(R.id.type);
  type.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,new String[]{"Casual","Medical","Duty"}));
  date.setOnClickListener(v->{Calendar c=Calendar.getInstance();new DatePickerDialog(this,(picker,y,m,d)->{
   chosen=d+"/"+(m+1)+"/"+y;dateText.setText(chosen);
  },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();});
  apply.setOnClickListener(v->{if(chosen.isEmpty()){Toast.makeText(this,"Choose date",0).show();return;}
   int n=sp.getInt(user+"_count",0);sp.edit().putInt(user+"_count",n+1)
    .putString(user+"_date"+n,chosen).putString(user+"_type"+n,type.getSelectedItem().toString())
    .putString(user+"_status"+n,"Pending").apply();
   status.setText("Leave applied - Status: Pending");Toast.makeText(this,"Leave submitted",0).show();});
  showStatus();
 }
 @Override public boolean onCreateOptionsMenu(Menu m){m.add(0,1,0,"Apply Leave");m.add(0,2,0,"Check Status");m.add(0,3,0,"Leave Balance");return true;}
 @Override public boolean onOptionsItemSelected(MenuItem item){int id=item.getItemId();
  if(id==1){date.setVisibility(View.VISIBLE);apply.setVisibility(View.VISIBLE);type.setVisibility(View.VISIBLE);status.setText("Choose date and leave type");return true;}
  if(id==2){showStatus();return true;}if(id==3){int count=sp.getInt(user+"_count",0),approved=0;
   for(int j=0;j<count;j++)if(sp.getString(user+"_status"+j,"").equals("Approved"))approved++;
   status.setText("Remaining approved-leave quota: "+Math.max(0,12-approved));return true;}
  return super.onOptionsItemSelected(item);
 }
 void showStatus(){int n=sp.getInt(user+"_count",0);if(n==0){status.setText("No applications");return;}
  int last=n-1;String s=sp.getString(user+"_status"+last,"Pending");
  status.setText("Latest: "+sp.getString(user+"_type"+last,"")+" on "+sp.getString(user+"_date"+last,"")+"\nStatus: "+s+"\nTap here to update (lab demo)");
  status.setOnClickListener(v->new AlertDialog.Builder(this).setTitle("Demo: Update Status")
   .setItems(new String[]{"Approved","Rejected","Pending"},(dialog,which)->{
    String value=new String[]{"Approved","Rejected","Pending"}[which];
    sp.edit().putString(user+"_status"+last,value).apply();showStatus();
   }).show());
 }
}
