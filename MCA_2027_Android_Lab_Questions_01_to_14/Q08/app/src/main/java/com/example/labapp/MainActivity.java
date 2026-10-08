package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.view.*;import android.widget.*;
import java.util.*;
public class MainActivity extends Activity {
 String[] books={"Java Programming - Computing","Database Systems - Computing","Engineering Maths - Mathematics","Physics Fundamentals - Science","English Literature - Arts"};
 SharedPreferences sp;ListView list;TextView count,message;ArrayList<String> rows=new ArrayList<>();ArrayAdapter<String> adapter;int selected=0;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);
  sp=getSharedPreferences("library",0);list=findViewById(R.id.list);count=findViewById(R.id.count);message=findViewById(R.id.message);
  if(!sp.getBoolean("ready",false)){SharedPreferences.Editor e=sp.edit();for(int j=0;j<books.length;j++)e.putInt("stock"+j,2);e.putBoolean("ready",true).apply();}
  adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,rows);list.setAdapter(adapter);refresh();
  registerForContextMenu(list);
  list.setOnItemClickListener((p,v,pos,id)->message.setText(books[pos]+" - Available: "+sp.getInt("stock"+pos,0)));
 }
 void refresh(){rows.clear();int total=0;for(int j=0;j<books.length;j++){int stock=sp.getInt("stock"+j,0);total+=stock;rows.add(books[j]+"  ("+stock+" available)");}
  count.setText("Total available copies: "+total);adapter.notifyDataSetChanged();}
 @Override public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo info){super.onCreateContextMenu(menu,v,info);
  AdapterView.AdapterContextMenuInfo data=(AdapterView.AdapterContextMenuInfo)info;selected=data.position;
  menu.setHeaderTitle(books[selected]);menu.add(0,1,0,"View Details");menu.add(0,2,0,"Reserve Book");menu.add(0,3,0,"Add to Wishlist");}
 @Override public boolean onContextItemSelected(MenuItem item){int id=item.getItemId();
  AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();int pos=info==null?selected:info.position;
  if(id==1){new AlertDialog.Builder(this).setTitle("Book Details").setMessage(books[pos]+"\nAvailable copies: "+sp.getInt("stock"+pos,0)).setPositiveButton("OK",null).show();return true;}
  if(id==2){int stock=sp.getInt("stock"+pos,0);if(stock==0){Toast.makeText(this,"Out of stock",0).show();return true;}
   sp.edit().putInt("stock"+pos,stock-1).apply();refresh();Toast.makeText(this,"Book reserved",0).show();return true;}
  if(id==3){sp.edit().putBoolean("wish"+pos,true).apply();Toast.makeText(this,"Added to wishlist",0).show();return true;}
  return super.onContextItemSelected(item);
 }
}
