package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.widget.*;
import java.util.*;
public class MainActivity extends Activity {
 String[] names={"Rice","Milk","Bread","Eggs","Sugar"};int[] prices={60,30,40,7,45};
 SharedPreferences sp;Spinner items;EditText qty;TextView bill;StringBuilder rows=new StringBuilder();int total=0;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);
  sp=getSharedPreferences("grocery",0);items=findViewById(R.id.items);qty=findViewById(R.id.quantity);bill=findViewById(R.id.bill);
  if(!sp.getBoolean("catalogReady",false)){SharedPreferences.Editor e=sp.edit();for(int j=0;j<names.length;j++)e.putInt(names[j],prices[j]);e.putBoolean("catalogReady",true).apply();}
  items.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,names));
  findViewById(R.id.add).setOnClickListener(v->{String s=qty.getText().toString();if(s.isEmpty()){qty.setError("Enter quantity");return;}
   int q;try{q=Integer.parseInt(s);}catch(Exception ex){qty.setError("Invalid number");return;}
   if(q<=0){qty.setError("Quantity must be positive");return;}
   String n=items.getSelectedItem().toString();int price=sp.getInt(n,0),cost=price*q;total+=cost;
   rows.append(n).append("   ").append(q).append(" x Rs.").append(price).append(" = Rs.").append(cost).append("\n");
   qty.setText("");Toast.makeText(this,"Added",0).show();
  });
  findViewById(R.id.generate).setOnClickListener(v->bill.setText("INVOICE\n\n"+rows+"\nGrand Total: Rs."+total));
 }
}
