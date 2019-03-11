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

import java.util.ArrayList;
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
    int sum7;
    Spinner sp1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        sp1 = (Spinner) findViewById(R.id.spp1);
        wv = (WebView) findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        t=getIntent();
        Scountnow=t.getIntExtra("Scountnow",0);
        arrays=new int[Scountnow];

        List<String> list=new ArrayList<>();
        list.add("התקדמות שבועית");
        list.add("התקדמות חודשית");

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
        while (!c.isAfterLast()){
            Check=c.getInt(col1);
            sum=c.getInt(col2);
            weeknum=c.getInt(col3);
            alt.add(weeknum);
            c.moveToNext();
        }
        c.close();

        switch (Check){
            case 1: sum1=sum;
                break;
            case 2: sum2=sum;
                break;
            case 3: sum3=sum;
                break;
            case 4: sum4=sum;
                break;
            case 5: sum5=sum;
                break;
            case 6: sum6=sum;
                break;
            case 7: sum7=sum;
                break;
        }


        db.close();

        WebSofi = "https://www.sagive.co.il/library/tools/embed-graphs/bargraph/index.php?trcount=7&data=#8c0000,#910000,#297fb7,#1c81c4%7CSunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,%7C,%7CCount,%7Ccig count%7C";


       // WebSofi = "https://www.sagive.co.il/library/tools/embed-graphs/bargraph/index.php?trcount=7&data=#8c0000,#910000,#297fb7,#1c81c4%7CSunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,%7C"+sum1+sum2+sum3+sum4+sum5+sum6+sum7+",%7CCount,%7Ccig count%7C";
        wv.loadUrl(WebSofi);


    }

    public void mainn(View view) {
        Intent d = new Intent(this, IntroScreen.class);
        startActivity(d);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (sp1.getSelectedItem().equals("הצגה שבועית")) {
            WebSofi = "https://www.sagive.co.il/library/tools/embed-graphs/bargraph/index.php?trcount=7&data=#8c0000,#910000,#297fb7,#1c81c4%7CSunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,%7C"+sum1+sum2+sum3+sum4+sum5+sum6+sum7+",%7CCount,%7Ccig count%7C";
            wv.loadUrl(WebSofi);


        }
        if (sp1.getSelectedItem().equals("הצגה חודשית")) {
            WebSofi = "https://www.sagive.co.il/library/tools/embed-graphs/bargraph/index.php?trcount=7&data=#8c0000,#910000,#297fb7,#1c81c4%7CSunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,%7C"+sum1+sum2+sum3+sum4+sum5+sum6+sum7+",%7CCount,%7Ccig count%7C";
            wv.loadUrl(WebSofi);

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
