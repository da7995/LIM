package com.example.hp.lim;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText name, age, countnow, framenow,countgoal;
    EditText framegoal;
    String Sname;
//    Integer Sage;
    int Scountnow, Sframenow, Scountgoal, Sframegoal;
    Intent t;
    Spinner sp1, sp2, sp3, sp4;
    boolean wantchange=false;
    SQLiteDatabase db;
    HelperDB hlp;
    ContentValues cv;
    int d, day, week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText) findViewById(R.id.name);
        countnow=(EditText) findViewById(R.id.countnow);
        framenow=(EditText) findViewById(R.id.framenow);
        countgoal=(EditText) findViewById(R.id.countgoal);
        framegoal=(EditText) findViewById(R.id.framegoal);
        sp1=(Spinner) findViewById(R.id.sp1);
        sp2=(Spinner) findViewById(R.id.sp2);
        sp3=(Spinner) findViewById(R.id.sp3);
        sp4=(Spinner) findViewById(R.id.sp4);
        Intent d2=new Intent(this,IntroScreen.class);
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);
        week=calendar.get(Calendar.WEEK_OF_YEAR);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();


        Intent d=getIntent();
       wantchange= d.getBooleanExtra("wantchange",false);
       if (wantchange) {
           if (!sharedPref.getString("name", "").equals("")) {
               name.setText(sharedPref.getString("name", ""));
           }
           if (!sharedPref.getString("countnow", "").equals("")) {
               countnow.setText(sharedPref.getString("countnow", ""));
           }
           if (!sharedPref.getString("coutntgoal", "").equals("")) {
               countgoal.setText(sharedPref.getString("coutntgoal", ""));
           }
           if (!sharedPref.getString("framenow", "").equals("")) {
               framenow.setText(sharedPref.getString("framenow", ""));
           }
           if (!sharedPref.getString("framegoal", "").equals("")) {
               framegoal.setText(sharedPref.getString("framegoal", ""));
           }

       }
       else {

           if (!sharedPref.getString("name", "").equals("")) {
               startActivity(d2);
           }
           else
               name.setText(sharedPref.getString("name", ""));

           if (!sharedPref.getString("countnow", "").equals("")) {
               startActivity(d2);
           }
           else
               countnow.setText(sharedPref.getString("countnow", ""));
           if (!sharedPref.getString("coutntgoal", "").equals("")) {
               startActivity(d2);
           }
           else
               countgoal.setText(sharedPref.getString("coutntgoal", ""));
           if (!sharedPref.getString("framenow", "").equals("")) {
              startActivity(d2);
           }
           else
               framenow.setText(sharedPref.getString("framenow", ""));
           if (!sharedPref.getString("framegoal", "").equals("")) {
              startActivity(d2);
           }
           else
               framegoal.setText(sharedPref.getString("framegoal", ""));

       }




       List<String> list=new ArrayList<>();
       list.add("קופסאות");
       list.add("סיגריות");

        List<String> list2=new ArrayList<>();
        list2.add("ימים");
        list2.add("שבועות");



        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter1);
        sp1.setOnItemSelectedListener(this);



        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter2);
        sp2.setOnItemSelectedListener(this);


        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter3);
        sp3.setOnItemSelectedListener(this);



        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp4.setAdapter(adapter4);
        sp4.setOnItemSelectedListener(this);



    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        String text = parent.getItemAtPosition(i).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
       }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void main(View view) {
       if (sp1.getSelectedItem().equals("קופסאות")) {
            String st = countnow.getText().toString();
            Scountnow = Integer.parseInt(st);
            Scountnow = Scountnow * 20;

        }

        if (sp1.getSelectedItem().equals("סיגריות")) {
            String st = countnow.getText().toString();
            Scountnow = Integer.parseInt(st);

        }

        if (sp2.getSelectedItem().equals("ימים")) {
            String st = framenow.getText().toString();
            Sframenow = Integer.parseInt(st);

        }

        if (sp2.getSelectedItem().equals("שבועות")) {
            String st = framenow.getText().toString();
            Sframenow = Integer.parseInt(st);
            Sframenow=Sframenow*7;
        }

        if (sp3.getSelectedItem().equals("קופסאות")) {
            String st = countgoal.getText().toString();
            Scountgoal = Integer.parseInt(st);
            Scountgoal = Scountgoal * 20;

        }

        if (sp3.getSelectedItem().equals("סיגריות")) {
            String st = countgoal.getText().toString();
            Scountgoal = Integer.parseInt(st);

        }

        if (sp4.getSelectedItem().equals("ימים")) {
            String st = framegoal.getText().toString();
            Sframegoal= Integer.parseInt(st);

        }

        if (sp4.getSelectedItem().equals("שבועות")) {
            String st = framegoal.getText().toString();
            Sframegoal = Integer.parseInt(st);
            Sframegoal=Sframegoal*7;
        }

            if (name.getText().toString().isEmpty() ||
                countnow.getText().toString().isEmpty() ||
                framenow.getText().toString().isEmpty() ||
                countgoal.getText().toString().isEmpty() ||
                framegoal.getText().toString().isEmpty()){

            Toast.makeText(this, "אנא הכנס את כל הערכים", Toast.LENGTH_SHORT).show();

        }
        else {
            SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPref.edit();
            editor.putString("name",name.getText().toString());
            editor.putString("countnow",countnow.getText().toString());
            editor.putString("coutntgoal",countgoal.getText().toString());
            editor.putString("framenow",framenow.getText().toString());
            editor.putString("framegoal",framegoal.getText().toString());
            editor.apply();

            String user=sharedPref.getString("name","");
            Toast.makeText(this, user, Toast.LENGTH_SHORT).show();

                hlp = new HelperDB(this);
                db = hlp.getWritableDatabase();
                cv=new ContentValues();
                d=(Scountgoal-Scountnow)/(Sframegoal-1);
                int an=0;
                for (int i=1;i<Sframegoal+1;i++){
                    an=Scountnow+d*(i-1);
                    cv.put(PROGRAM.DAY4,day);
                    cv.put(PROGRAM.WEEKNUM4,week);
                    cv.put(PROGRAM.RESTRICT,an);
                    day++;
                    if (day>7) {
                        day = 1;
                        week++;
                        if (week>52){
                            week=1;
                        }
                    }
                    db.insert(PROGRAM.TABLE_PROGRAM,null,cv);
                }
                db.close();


            Sname=name.getText().toString();
          t = new Intent(this, IntroScreen.class);
            t.putExtra("Sname",Sname);
            t.putExtra("Scountnow",Scountnow);
            t.putExtra("Scountgal",Scountgoal);
            t.putExtra("Sframenow",Sframenow);
            t.putExtra("Sframegoal",Sframegoal);
            startActivity(t);
       }
    }


}
