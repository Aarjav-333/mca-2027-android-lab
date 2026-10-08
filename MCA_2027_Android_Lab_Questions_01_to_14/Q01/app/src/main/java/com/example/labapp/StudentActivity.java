package com.example.labapp;
import android.app.*;import android.os.*;import android.content.*;import android.view.*;import android.widget.*;
public class StudentActivity extends Activity {
 SharedPreferences sp;TextView q,old;RadioGroup group;RadioButton[] radios=new RadioButton[4];Button next;
 String user;int index=0,score=0,total;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_student);
  sp=getSharedPreferences("quiz",0);user=sp.getString("user","");if(user.isEmpty()||user.equals("creator")){finish();return;}
  q=findViewById(R.id.question);old=findViewById(R.id.old);group=findViewById(R.id.group);next=findViewById(R.id.next);
  int[] ids={R.id.r1,R.id.r2,R.id.r3,R.id.r4};for(int j=0;j<4;j++)radios[j]=findViewById(ids[j]);
  total=sp.getInt("count",0);
  old.setText("Previous marks: "+sp.getString("marks_"+user,"Not available"));
  if(total==0){q.setText("Creator has not added questions");next.setEnabled(false);}else showQuestion();
  next.setOnClickListener(v->{int id=group.getCheckedRadioButtonId();if(id==-1){Toast.makeText(this,"Select an answer",0).show();return;}
   int chosen=0;for(int j=0;j<4;j++)if(radios[j].getId()==id)chosen=j+1;
   if(chosen==sp.getInt("ans"+index,0))score++;index++;
   if(index<total)showQuestion();else{String marks=score+"/"+total;sp.edit().putString("marks_"+user,marks).apply();q.setText("Final Marks: "+marks);old.setText("Saved: "+marks);group.setVisibility(View.GONE);next.setVisibility(View.GONE);}
  });
  findViewById(R.id.logout).setOnClickListener(v->{sp.edit().remove("user").apply();startActivity(new Intent(this,MainActivity.class));finish();});
 }
 void showQuestion(){q.setText((index+1)+". "+sp.getString("q"+index,""));String[] keys={"a","b","c","d"};for(int j=0;j<4;j++)radios[j].setText(sp.getString(keys[j]+index,""));group.clearCheck();next.setText(index==total-1?"Submit":"Next");}
}
