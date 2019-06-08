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

import static com.example.hp.lim.DAY.TABLE_DAY;
import static com.example.hp.lim.PROGRAM.RESTRICT;
import static com.example.hp.lim.PROGRAM.TABLE_PROGRAM;
import static com.example.hp.lim.TOTAL.TABLE_TOTAL;
import static com.example.hp.lim.TOTAL.TOTAL1;
import static com.example.hp.lim.WEEK.TABLE_WEEK;

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
     ContentValues cv1;
     ContentValues cv2;
     ContentValues cv3;
     ContentValues cv4;
     ContentValues cv5;
     ContentValues cv6;
     int count=0;
     int day;
     int Check;
     int Check4;
     int week;
     int weeknum;
     int countWeek=0;


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
        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);
        week=calendar.get(Calendar.WEEK_OF_YEAR);
        String weekS = Integer.toString(week);
       /*  hlp = new HelperDB(this);
         db = hlp.getWritableDatabase();
         Cursor c = db.query(TABLE_PROGRAM,new String[]{PROGRAM.RESTRICT}, PROGRAM.WEEKNUM4+"=?", new String[]{weekS}, null, null, null);
         int col1 = c.getColumnIndex(RESTRICT);
         int i=1;
         while (i<day){
             c.moveToNext();
             i++;
         }
         int pro=c.getInt(col1);
         c.close();
         db.close();
         int rest=pro-count;
         if (rest>0)
        tv.setText("שלום "+name+"נותרו לך עוד "+rest+"סיגריות לעשן היום");
         else
             tv.setText("שלום "+name+"נותרו לך עוד 0 סיגריות לעשן היום");

*/
}

     @RequiresApi(api = Build.VERSION_CODES.N)
     public void OpenBox(View view) {
         //  byte [] bytes = string.getBytes(Charset.defaultCharset());
         // mBlurtoothConnection.write(bytes);
         if (count == 0) {
             hlp = new HelperDB(this);
             db = hlp.getWritableDatabase();
             cv4 = new ContentValues();
             cv4.put(DAY.DATE, day);
             db.insert(TABLE_DAY, null, cv4);
             db.close();

         } else {
             hlp = new HelperDB(this);
             db = hlp.getWritableDatabase();

             Cursor c = db.query(TABLE_DAY, null, null, null, null, null, null);
             int col1 = c.getColumnIndex("Date");
             c.moveToLast();
             Check = c.getInt(col1);
             if (Check == day) {
                 cv = new ContentValues();
                 cv.put(DAY.DATE, day);
                 cv.put(DAY.WEEKNUM, week);
                 db.insert(TABLE_DAY, null, cv);
                 db.close();
                 count++;
             } else {
                 count = 1;
                 String y = "" + Check;
                 String unreadquery = "SELECT COUNT(*) FROM "
                         + TABLE_DAY + " WHERE " + DAY.DATE + "='" + y + "'";
                 int a = c.getCount();
                 Toast.makeText(this, "" + a, Toast.LENGTH_SHORT).show();
                 cv2 = new ContentValues();
                 cv6 = new ContentValues();
                 cv2.put(DAY.DATE, day);
                 cv2.put(DAY.WEEKNUM, week);
                 cv6.put(WEEK.SUM, a);
                 cv6.put(WEEK.WEEKNUM2, week);
                 hlp = new HelperDB(this);
                 db = hlp.getWritableDatabase();
                 db.insert(TABLE_DAY, null, cv2);
                 db.insert(TABLE_WEEK, null, cv6);
                 db.close();
             }
         }
         if (countWeek == 0) {
             hlp = new HelperDB(this);
             db = hlp.getWritableDatabase();
             cv5 = new ContentValues();
             cv5.put(WEEK.WEEKNUM2, week);
             db.insert(TABLE_WEEK, null, cv5);
             db.close();
         } else {
             db = hlp.getWritableDatabase();
             db = hlp.getWritableDatabase();
             Cursor c4 = db.query(TABLE_WEEK, null, null, null, null, null, null);
             int col4 = c4.getColumnIndex("Weeknum2");
             c4.moveToLast();
             Check4 = c4.getInt(col4);
             db.close();
             if (Check4 == week) {
                 hlp = new HelperDB(this);
                 db = hlp.getWritableDatabase();
                 cv1 = new ContentValues();
                 cv1.put(WEEK.WEEKNUM2, week);
                 db.insert(TABLE_WEEK, null, cv1);
                 db.close();
             } else {
                 String d = "" + Check4;
                 String unreadquery4 = "SELECT COUNT(*) FROM "
                         + TABLE_WEEK + " WHERE " + WEEK.SUM + "='" + d + "'";
                 int a4 = c4.getCount();

                 Toast.makeText(this, "" + a4, Toast.LENGTH_SHORT).show();
                 hlp = new HelperDB(this);
                 db = hlp.getWritableDatabase();
                 cv3 = new ContentValues();
                 cv3.put(TOTAL.TOTAL1, a4);
                 cv3.put(TOTAL.WEEKNUM3, week);
                 db.insert(TABLE_TOTAL, null, cv3);
                 db.close();

             }
         }
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
