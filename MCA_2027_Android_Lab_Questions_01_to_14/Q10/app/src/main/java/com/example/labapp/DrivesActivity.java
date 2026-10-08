package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.view.*;import android.widget.*;
import java.util.*;
public class DrivesActivity extends Activity {
 SharedPreferences sp;ArrayList<String> rows=new ArrayList<>();ArrayAdapter<String> adapter;ListView list;EditText cgpa;CheckBox backlogs,resume;TextView details;boolean officer;int selected=-1;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_drives);
  sp=getSharedPreferences("placement",0);officer=sp.getString("role","student").equals("officer");
  list=findViewById(R.id.list);cgpa=findViewById(R.id.cgpa);backlogs=findViewById(R.id.backlogs);resume=findViewById(R.id.resume);details=findViewById(R.id.details);
  ((TextView)findViewById(R.id.heading)).setText(officer?"Officer - Post Jobs from Menu":"Student - Placement Drives");
  if(!sp.contains("count"))sp.edit().putInt("count",1).putString("company0","Tech Solutions").putFloat("cutoff0",7.0f).putInt("applicants0",0).apply();
  adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,rows);list.setAdapter(adapter);refresh();
  if(officer){cgpa.setVisibility(View.GONE);backlogs.setVisibility(View.GONE);resume.setVisibility(View.GONE);findViewById(R.id.apply).setVisibility(View.GONE);}
  list.setOnItemClickListener((p,v,pos,id)->{selected=pos;showDetails();});
  findViewById(R.id.apply).setOnClickListener(v->apply());
 }
 void refresh(){rows.clear();for(int j=0;j<sp.getInt("count",0);j++)rows.add(sp.getString("company"+j,"")+" - Min CGPA "+sp.getFloat("cutoff"+j,0)+" | Applicants: "+sp.getInt("applicants"+j,0));adapter.notifyDataSetChanged();}
 void showDetails(){if(selected<0)return;details.setText("Company: "+sp.getString("company"+selected,"")+"\nCGPA cutoff: "+sp.getFloat("cutoff"+selected,0)+"\nApplicants: "+sp.getInt("applicants"+selected,0));}
 void apply(){if(selected<0){Toast.makeText(this,"Select a drive",0).show();return;}
  double c;try{c=Double.parseDouble(cgpa.getText().toString());}catch(Exception ex){cgpa.setError("Enter CGPA");return;}
  if(c<0||c>10){cgpa.setError("CGPA must be 0-10");return;}
  if(c<sp.getFloat("cutoff"+selected,0)||!backlogs.isChecked()||!resume.isChecked()){
   new AlertDialog.Builder(this).setMessage("Not eligible: Check CGPA and prerequisites").setPositiveButton("OK",null).show();return;}
  new AlertDialog.Builder(this).setTitle("Eligible!").setMessage("Apply to "+sp.getString("company"+selected,"")+"?")
   .setPositiveButton("Apply",(d,w)->{sp.edit().putInt("applicants"+selected,sp.getInt("applicants"+selected,0)+1).apply();refresh();showDetails();Toast.makeText(this,"Application submitted",0).show();}).setNegativeButton("Cancel",null).show();
 }
 @Override public boolean onCreateOptionsMenu(Menu menu){if(officer)menu.add(0,1,0,"Post New Drive");return true;}
 @Override public boolean onOptionsItemSelected(MenuItem item){if(item.getItemId()!=1)return super.onOptionsItemSelected(item);
  LinearLayout panel=new LinearLayout(this);panel.setOrientation(LinearLayout.VERTICAL);panel.setPadding(25,10,25,10);
  EditText company=new EditText(this),cutoff=new EditText(this);company.setHint("Company Name");cutoff.setHint("Minimum CGPA");cutoff.setInputType(8194);panel.addView(company);panel.addView(cutoff);
  AlertDialog dialog=new AlertDialog.Builder(this).setTitle("Post Drive").setView(panel).setPositiveButton("Save",null).setNegativeButton("Cancel",null).create();
  dialog.setOnShowListener(d->dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v->{String name=company.getText().toString().trim();double min;
   try{min=Double.parseDouble(cutoff.getText().toString());}catch(Exception ex){cutoff.setError("Enter cutoff");return;}
   if(name.isEmpty()){company.setError("Required");return;}if(min<0||min>10){cutoff.setError("0-10 only");return;}
   int n=sp.getInt("count",0);sp.edit().putInt("count",n+1).putString("company"+n,name).putFloat("cutoff"+n,(float)min).putInt("applicants"+n,0).apply();refresh();dialog.dismiss();
  }));dialog.show();return true;
 }
}
