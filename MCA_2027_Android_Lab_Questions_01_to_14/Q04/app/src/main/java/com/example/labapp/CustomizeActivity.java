package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.widget.*;
public class CustomizeActivity extends Activity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_customize);
  RadioGroup sizes=findViewById(R.id.sizes);CheckBox cheese=findViewById(R.id.cheese),olive=findViewById(R.id.olive),mush=findViewById(R.id.mushroom);Spinner crust=findViewById(R.id.crust);
  String[] crusts={"Thin Crust","Thick Crust","Cheese Burst (+Rs.80)"};
  crust.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,crusts));
  findViewById(R.id.save).setOnClickListener(v->{int checked=sizes.getCheckedRadioButtonId();if(checked==-1){Toast.makeText(this,"Choose size",0).show();return;}
   int price=150;String size="Small";
   if(checked==R.id.size1){price=250;size="Medium";}if(checked==R.id.size2){price=350;size="Large";}
   String text=size+" Pizza: Rs."+price+"\n";
   if(cheese.isChecked()){price+=50;text+="Extra Cheese: Rs.50\n";}
   if(olive.isChecked()){price+=30;text+="Olives: Rs.30\n";}
   if(mush.isChecked()){price+=40;text+="Mushroom: Rs.40\n";}
   text+="Crust: "+crust.getSelectedItem().toString()+"\n";
   if(crust.getSelectedItemPosition()==2)price+=80;
   getSharedPreferences("pizza",0).edit().putString("summary",text).putInt("total",price).apply();
   startActivity(new Intent(this,ViewOrderActivity.class));
  });
 }
}
