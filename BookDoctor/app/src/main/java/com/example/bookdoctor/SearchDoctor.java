package com.example.bookdoctor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.HashMap;


public class SearchDoctor extends AppCompatActivity {
    private static String[] SUGGESTION = new String[] {
                    " Vedhas Nimkar Internal Medicine","General Physician","Pulmonologist","General Surgeon","Pediatric Cardiologist","Diabetologist ndocrinologist",
                    "Plastic Surgeon","Dermatologist","Gastroenterologist","Cardiologists"
            };
    private MaterialSearchView materialSearchView;
    private ArrayList<String> slotdata;
    ListView listView;
    ArrayAdapter arrayAdapter;
    Button button;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor);
        listView=(ListView)findViewById(R.id.doctor_list_text);
        Toolbar toolbar=findViewById(R.id.toolbar);
        button=findViewById(R.id.done);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        materialSearchView = findViewById(R.id.search_view);
        materialSearchView.setSuggestions(SUGGESTION);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeSchedule();

            }
        });


        final ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Dr. Vedhas Nimkar\tInternal Medicine\n"+"₹500 "+"Mon-Sat" +
                "10:00 AM-07:00 PM");
        arrayList.add("Dr. Navin R. Davda General Physician\n" +" ₹200 \t"+" Mon-Fri\t" +
                       " 07:00 PM-08:00 PM");
        arrayList.add("₹1,000 \t"+"Mon-Wed, Fri-Sat\t" +
                        "10:00 AM-12:00 PM");
        arrayList.add("Dr. Pankaj Joshi\t General Surgery, FICS\n" +
               "₹650 \t"+"Mon-Sat\t" +
                "09:00 AM-05:00 PM");
        arrayList.add("Dr. Srinivas L\tPediatric Cardiology\n" +
                "₹650 \t"+"Mon-Sat\t" +
                "09:00 AM-05:00 PM");
        arrayList.add("Dr. Mukta Bapat\tGastroenterology\n" +
               "₹1,000 \t"+"Tue, Fri\t" +
               "05:00 PM-07:00 PM" );


        /*materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }*/

            /*@Override
            public void onSearchViewClosed() {
               // if(arrayAdapter.getCount()>0)
                arrayAdapter.clear();
                arrayAdapter.addAll(SUGGESTION);
                arrayAdapter.notifyDataSetChanged();
            }
        });*/
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.clear();
                if(newText !=null && !newText.isEmpty())
                {
                    for (String s : SUGGESTION)
                    {
                        if (s.toLowerCase().contains(newText))
                            arrayAdapter.add(s);
                    }
                }else
                {
                    arrayAdapter.addAll(SUGGESTION);
                }
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem=menu.findItem(R.id.searchMenu);
        materialSearchView.setMenuItem(menuItem);
        return super.onCreateOptionsMenu(menu);
    }
    public void TimeSchedule(){
        Intent intent = new Intent(this,TimeSchedule.class);
        startActivity(intent);
    }

}


