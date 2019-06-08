package com.example.hp.lim;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Graphs extends AppCompatActivity   implements AdapterView.OnItemSelectedListener{
    WebView wv;
    String WebSofi;
    SQLiteDatabase db;
    HelperDB hlp;
    int Check;
    ArrayList<Integer> alt = new ArrayList<>();
    int sum;
    int weeknum;
    int[]arrays;
    Intent t;
    int Scountnow;
    int sumday;
    int sum1;
    int sum2;
    int sum3;
    int sum4;
    int sum5;
    int sum6;
    int week;
    int weeknum3;
    int sum7;
    Spinner sp1;
    int tot1;
    int tot2;
   int tot;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        week=Calendar.WEEK_OF_YEAR;
        sp1 = (Spinner) findViewById(R.id.spp1);
        wv = (WebView) findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        t=getIntent();
        Scountnow=t.getIntExtra("Scountnow",0);

        List<String> list=new ArrayList<>();
        list.add("התקדמות יומית");
        list.add("התקדמות שבועית");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter1);
        sp1.setOnItemSelectedListener(this);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        Cursor c=db.query(WEEK.TABLE_WEEK,null,null,null,null,null,null);
        int col1=c.getColumnIndex("Date2");
        int col2=c.getColumnIndex("Sum");
        int col3=c.getColumnIndex("Weeknum2");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Check = c.getInt(col1);
            sum = c.getInt(col2);
            weeknum = c.getInt(col3);
            c.moveToNext();
            switch (Check) {
                case 1:
                    sum1 = sum;
                    Toast.makeText(this, "" + sum1, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    sum2 = sum;
                    Toast.makeText(this, "" + sum2, Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    sum3 = sum;
                    Toast.makeText(this, "" + sum3, Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    sum4 = sum;
                    Toast.makeText(this, "" + sum4, Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    sum5 = sum;
                    Toast.makeText(this, "" + sum5, Toast.LENGTH_SHORT).show();
                    break;
                case 6:
                    sum6 = sum;
                    Toast.makeText(this, "" + sum6, Toast.LENGTH_SHORT).show();
                    break;
                case 7:
                    sum7 = sum;
                    Toast.makeText(this, "" + sum7, Toast.LENGTH_SHORT).show();
                    break;
            }
            db.close();


            hlp = new HelperDB(this);
            db = hlp.getWritableDatabase();
            Cursor c1 = db.query(TOTAL.TABLE_TOTAL, null, null, null, null, null, null);
            int coll1 = c.getColumnIndex("Total");
            int coll3 = c.getColumnIndex("Weeknum3");
            c1.moveToFirst();
            while (!c1.isAfterLast()) {
                tot = c1.getInt(coll1);
                weeknum3 = c.getInt(coll3);
                c1.moveToNext();
                if (weeknum3 == week) {
                    tot1 = tot;
                }
                if (weeknum3 == week - 1) {
                    tot2 = tot;
                }


            } db.close();

        }
    }

    public void mainn(View view) {
        Intent d = new Intent(this, IntroScreen.class);
        startActivity(d);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (sp1.getSelectedItem().equals("התקדמות יומית")) {
            WebSofi = "https://www.sagive.co.il/library/tools/embed-graphs/bargraph/index.php?trcount=7&data=#8c0000,#910000,#297fb7,#1c81c4%7CSunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,%7C" + sum1 + "," + sum2 + "," + sum3 + "," + sum4 + "," + sum5 + "," + sum6 + "," + sum7 + ",%7CCount,%7Ccig count%7C";
            wv.loadUrl(WebSofi);
        }
        if (sp1.getSelectedItem().equals("התקדמות שבועית")) {
            WebSofi = "https://www.sagive.co.il/library/tools/embed-graphs/bargraph/index.php?trcount=7&data=#8c0000,#910000,#297fb7,#1c81c4%7Cthis week,last week,%7C"+tot1 +","+tot2+",%7CCount,%7Ccig count%7C";
            wv.loadUrl(WebSofi);

        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
