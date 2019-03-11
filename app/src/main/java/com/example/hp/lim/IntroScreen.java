 package com.example.hp.lim;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.hp.lim.PROGRAM.RESTRICT;

 public class IntroScreen extends AppCompatActivity {
     String string ="lock";
     BluetoothConnectionService mBlurtoothConnection;
     Intent t;
     String name;
     int Scountnow;
     int Scountgoal;
     int Sframenow;
     int Sframegoal;
     int x;
     SQLiteDatabase db;
     HelperDB hlp;
     TextView tv ;
     @RequiresApi(api = Build.VERSION_CODES.N)
     ContentValues cv;
     int day;
     int Check;
     int sumday;
     int week;
     int[]arrays;
     int weeknum;
     ArrayList <Integer>  alt = new ArrayList<>();
     ArrayList <Integer>  alt3 = new ArrayList<>();
     ArrayList <Integer>  alt4 = new ArrayList<>();
     ArrayList <Integer>  alt2 = new ArrayList<>();
     int dayyyy;
     int Restrict;
     int [] program;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        tv = (TextView) findViewById( R.id.textView);
        t=getIntent();
        name=t.getStringExtra("Sname");
        Scountnow=t.getIntExtra("Scountnow",0);
        Scountgoal=t.getIntExtra("Scountgoal",0);
        Sframegoal=t.getIntExtra("Sframegoal",0);
        Sframenow=t.getIntExtra("Sframenow",0);
//        x=(Scountnow-Scountgoal)/Sframegoal;
        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();


        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);
        week=calendar.get(Calendar.WEEK_OF_YEAR);


         hlp = new HelperDB(this);
         db = hlp.getWritableDatabase();
         Cursor cc=db.query(PROGRAM.RESTRICT,null,null,null,null,null,null);
         int col1=cc.getColumnIndex("Restrict");
         int col2=cc.getColumnIndex("Day");
         cc.moveToFirst();
         program=new int[Sframegoal+1];
         while (!cc.isAfterLast()){
             dayyyy=cc.getInt(col1);
             Restrict=cc.getInt(col2);
             alt3.add(Restrict);
             alt3.add(dayyyy);
             cc.moveToNext();
         }
         cc.close();

         for (int i=0;i<Sframegoal;i++){
             if( alt4.get(i) != alt4.get(i+1) ){
             tv.setText("שלום אורח, נותרו לך עוד"+alt3.get(i++)+"סיגריות לעשן היום");
         }
         else {
                 tv.setText("שלום אורח, נותרו לך עוד"+alt3.get(i)+"סיגריות לעשן היום");

             }

     }
     }

     @RequiresApi(api = Build.VERSION_CODES.N)
     public void OpenBox(View view) {
      //  byte [] bytes = string.getBytes(Charset.defaultCharset());
       // mBlurtoothConnection.write(bytes);

         hlp = new HelperDB(this);
         db = hlp.getWritableDatabase();
         cv=new ContentValues();
         cv.put(DAY.DATE,day);
         cv.put(DAY.WEEKNUM,week);
         db.insert(DAY.TABLE_DAY,null,cv);
         db.close();

         hlp = new HelperDB(this);
         db = hlp.getWritableDatabase();
         Cursor c=db.query(DAY.TABLE_DAY,null,null,null,null,null,null);
         int col1=c.getColumnIndex("Date");
         int col2=c.getColumnIndex("Weeknum");
         c.moveToFirst();

         while (!c.isAfterLast()){
             Check=c.getInt(col1);
             weeknum=c.getInt(col2);
             alt.add(Check);
             c.moveToNext();
         }
            c.close();

         for (int i=0;i<alt.size()-1;i++){
                 sumday++;
                 if( alt.get(i) != alt.get(i+1) ){
                     cv.put(WEEK.SUM,sumday);
                     cv.put(WEEK.DATE2,day);
                     cv.put(WEEK.WEEKNUM2,weeknum);
                     db.insert(WEEK.TABLE_WEEK,null,cv);
                 }
         }

    /*  Cursor c2=db.query(WEEK.TABLE_WEEK,null,null,null,null,null,null,null);
         int col4=c.getColumnIndex("Date2");
         int col5=c.getColumnIndex("Sum");
         int col3=c.getColumnIndex("Weeknum2");
         c2.moveToFirst();
         while (!c2.isAfterLast()){
             weeeek=c2.getInt(col4);
             sum=c2.getInt(col5);
             weeknum2=c.getInt(col3);
             alt2.add(weeknum2);
             c2.moveToNext();
         }
         c2.close();

        for (int i=0;i<alt2.size()-1;i++){
            sumweek=alt2.addAll(i);
             if( alt2.get(i) != alt2.get(i+1) ){
                 cv.put(TOTAL.TOTAL1,sumweek);
                 cv.put(TOTAL.WEEKNUM3,weeknum2);
                 db.insert(TOTAL.TABLE_TOTAL,null,cv);
             }
         }

       */  db.close();


         }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main, menu);
         return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         if (item.getTitle().toString().equals("מסך ההתקדמות")){
             Intent d=new Intent(this,Graphs.class);
             d.putExtra("Scountnow",Scountnow);
             startActivity(d);
         }

         if (item.getTitle().toString().equals("לעזרה והסבר על האפליקציה")){
             Intent d=new Intent(this,Help.class);
             startActivity(d);
         }

         if (item.getTitle().toString().equals("לשינוי הגדרות התחלתיות")){
             Intent d=new Intent(this,MainActivity.class);
             d.putExtra("wantchange",true);
             startActivity(d);
         }

         if (item.getTitle().toString().equals("התחברות לבלוטוס")){
            Intent d=new Intent(this,Blue.class);
             startActivity(d);
         }

         if (item.getTitle().toString().equals("קרדיטים ")){
             Intent d=new Intent(this,credits.class);
             startActivity(d);
         }

         return true;
     }
 }
