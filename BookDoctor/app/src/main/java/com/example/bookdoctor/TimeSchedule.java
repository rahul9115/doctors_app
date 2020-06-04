package com.example.bookdoctor;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import android.widget.DatePicker;
import android.widget.TextView;
import android.view.View;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.widget.Toast;
public class TimeSchedule extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    //private TextView dateView;
    private int year, month, day;
    Button button;
private boolean showDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_schedule);
        ListView listView=findViewById(R.id.time);
        Button button=findViewById(R.id.done);
        LinearLayout dateLayout=(LinearLayout)this.findViewById(R.id.dateLayout);
        dateLayout.setVisibility(View.VISIBLE);

        LinearLayout listLayout=(LinearLayout)this.findViewById(R.id.listLayout);
        listLayout.setVisibility(View.GONE);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        ArrayList<String> time= new ArrayList<>();
      /*  time.add("10.00AM - 10.30AM");
        time.add("10.30AM - 11.00AM");
        time.add("11.00AM - 11.30AM");
        time.add("11.30AM - 12.00PM");
        time.add("12.00PM - 12.30PM");
        time.add("12.30PM - 01.30PM");
        time.add("02.30PM - 03.00PM");
        time.add("03.00PM - 03.30PM");
        time.add("03.30PM - 04.00PM");
        time.add("04.00PM - 04.30PM");
        time.add("04.30PM - 05.00PM");
        time.add("05.00PM - 05.30PM");
        time.add("05.30PM - 06.00PM");
        time.add("06.00PM - 06.30PM");
        time.add("06.30PM - 07.00PM");
        time.add("07.00PM - 07.30PM");
        time.add("07.30PM - 08.00PM");*/
        //getTimeSet(false);


    }


    private void getTimeSet(boolean isCurrentDay,String selecteddate) {
        ArrayList results = new ArrayList<String>();
        DbHandlerTimeSlot dbHandler = new DbHandlerTimeSlot(TimeSchedule.this);

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        for (int i = 0; i < 9; i++) {
            int n = 30;
            Calendar calendar = new GregorianCalendar();
            if (!isCurrentDay) {
                calendar.set(Calendar.HOUR_OF_DAY, 9);
                calendar.add(Calendar.HOUR_OF_DAY, -i);

                calendar.set(Calendar.MINUTE, 0);
                calendar.add(Calendar.MINUTE, n);

                calendar.set(Calendar.SECOND, 0);

                String day1 = sdf.format(calendar.getTime());
                calendar.add(Calendar.HOUR, 0);
                calendar.add(Calendar.MINUTE, n);

                String day2 = sdf.format(calendar.getTime());
                String day = day1 + " - " + day2;
                //results.add(i, day);
                dbHandler.insertData(day1, day2, selecteddate, "test1", "0");
                System.out.println(results);
                n += 30;
            }
        }
    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        showDialog=true;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);


                }
            };

    private void showDate(int year, int month, int day) {
     /*   dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));*/
       String date=new StringBuilder().append(day).append("/").append(month).append("/").append(year).toString();
        DbHandlerTimeSlot dbHandler = new DbHandlerTimeSlot(TimeSchedule.this);
        boolean selectedDateFlag= dbHandler.isDateAvialabe(date);
        if(!selectedDateFlag && showDialog) {
            getTimeSet(false, date);
            LinearLayout listLayout=(LinearLayout)this.findViewById(R.id.listLayout);
            listLayout.setVisibility(View.VISIBLE);
            LinearLayout dateLayout=(LinearLayout)this.findViewById(R.id.dateLayout);
            dateLayout.setVisibility(View.GONE);
          //  DbHandlerTimeSlot dbHandler = new DbHandlerTimeSlot(TimeSchedule.this);
            ListView lv = (ListView) findViewById(R.id.timeList);
            ArrayList<HashMap<String, String>> userList=dbHandler.GetUsers(date);
            //final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,arrayList);
            ListAdapter adapter = new SimpleAdapter(TimeSchedule.this, userList, R.layout.list_row,
                    new String[]{"startTime","endTime","startDate"}, new int[]{R.id.startTime, R.id.endTime, R.id.startDate});
            lv.setAdapter(adapter);
            showDialog=false;

        }else{
            Toast.makeText(getApplicationContext(), "Slot for this date is already available",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
    public void TimeSchedule(){
        Intent intent = new Intent(this,TimeSchedule.class);
        startActivity(intent);
    }
}
