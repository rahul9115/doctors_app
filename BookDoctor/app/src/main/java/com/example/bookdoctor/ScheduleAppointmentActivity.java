package com.example.bookdoctor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScheduleAppointmentActivity extends AppCompatActivity {

    private static final String TAG= "ScheduleAppointmentActivity";
    private TextView DisplayDate;
    Date date;
    Button button;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);
        DisplayDate=(TextView)findViewById(R.id.date);
       button=(Button) findViewById(R.id.search_dr);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SearchDoctor();

           }
       });

    };
    public void SearchDoctor(){
        Intent intent = new Intent(this, SearchDoctor.class);
        startActivity(intent);
        String dt;
        Date cal = (Date) Calendar.getInstance().getTime();
        dt = cal.toLocaleString();
        DisplayDate.setText(dt.toString());
        DisplayDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year= cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(ScheduleAppointmentActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });
        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
               // Log.d("onDateSet: date: " + i + "/" + i1 + "/" + i2);
                Log.w("DatePicker","onDateSet: mm/dd/yyyy = " + day + "/" + month + "/" + year);
                String date = day + "/" + month + "/" + year;
                DisplayDate.setText(date);

            }
        };
    }
}
