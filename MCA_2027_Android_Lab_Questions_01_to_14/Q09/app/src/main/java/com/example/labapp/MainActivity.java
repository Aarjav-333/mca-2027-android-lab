package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.view.*;import android.widget.*;
import java.util.*;import java.text.*;
public class MainActivity extends Activity {
 SharedPreferences sp;Spinner category;EditText desc;CheckBox admin;ListView list;ArrayList<Integer> indexes=new ArrayList<>();ArrayList<String> rows=new ArrayList<>();ArrayAdapter<String> adapter;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);
  sp=getSharedPreferences("grievance",0);category=findViewById(R.id.category);desc=findViewById(R.id.description);admin=findViewById(R.id.admin);list=findViewById(R.id.tickets);
  category.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,new String[]{"Academic","Infrastructure","Hostel"}));
  adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,rows);list.setAdapter(adapter);refresh("none");
  findViewById(R.id.submit).setOnClickListener(v->{String s=desc.getText().toString().trim();if(s.isEmpty()){desc.setError("Enter details");return;}
   int n=sp.getInt("count",0),id=1001+n;String timestamp=new SimpleDateFormat("dd-MM-yyyy HH:mm",Locale.getDefault()).format(new Date());
   String c=category.getSelectedItem().toString(),priority=c.equals("Academic")?"High":c.equals("Hostel")?"Medium":"Low";
   sp.edit().putInt("count",n+1).putString("id"+n,"GR"+id).putString("desc"+n,s).putString("cat"+n,c)
     .putString("priority"+n,priority).putString("status"+n,"Open").putString("time"+n,timestamp).apply();
   desc.setText("");refresh("none");Toast.makeText(this,"Tracking ID: GR"+id,1).show();});
  list.setOnItemLongClickListener((p,v,pos,id)->{if(!admin.isChecked()){Toast.makeText(this,"Admin only",0).show();return true;}
   int k=indexes.get(pos);new AlertDialog.Builder(this).setTitle("Update Ticket")
    .setItems(new String[]{"Resolved","Open"},(d,which)->{sp.edit().putString("status"+k,which==0?"Resolved":"Open").apply();refresh("none");}).show();return true;});
  admin.setOnCheckedChangeListener((v,checked)->invalidateOptionsMenu());
 }
 void refresh(String sort){indexes.clear();rows.clear();for(int j=0;j<sp.getInt("count",0);j++)indexes.add(j);
  if(sort.equals("priority"))Collections.sort(indexes,(a,b)->rank(sp.getString("priority"+a,""))-rank(sp.getString("priority"+b,"")));
  if(sort.equals("status"))Collections.sort(indexes,(a,b)->sp.getString("status"+a,"").compareTo(sp.getString("status"+b,"")));
  for(int j:indexes)rows.add(sp.getString("id"+j,"")+"  "+sp.getString("cat"+j,"")+"  ["+sp.getString("priority"+j,"")+" / "+sp.getString("status"+j,"")+ "]\n"+sp.getString("time"+j,"")+"\n"+sp.getString("desc"+j,""));
  adapter.notifyDataSetChanged();
 }
 int rank(String p){if(p.equals("High"))return 0;if(p.equals("Medium"))return 1;return 2;}
 @Override public boolean onCreateOptionsMenu(Menu menu){if(admin!=null&&admin.isChecked()){menu.add(0,1,0,"Sort by Priority");menu.add(0,2,0,"Sort by Status");}return true;}
 @Override public boolean onOptionsItemSelected(MenuItem item){if(item.getItemId()==1){refresh("priority");return true;}if(item.getItemId()==2){refresh("status");return true;}return super.onOptionsItemSelected(item);}
}
