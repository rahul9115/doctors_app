package com.example.bookdoctor;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import patient.patient;

public class LoginPage extends AppCompatActivity {

    TextView textView;
    Button button;
    EditText Email,password;
    RelativeLayout relativeLayout;
    Boolean email=false, pass=false,valid=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        textView = findViewById(R.id.textView);
        relativeLayout=findViewById(R.id.login);
        Email=findViewById(R.id.email);
        password=findViewById(R.id.Password);
        button=findViewById(R.id.button);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                if(Email.getText().toString().length()==0){
                    Email.setError("Email is not Entered");
                    Email.requestFocus();
                    email=false;
                }else{
                    email=true;
                }
                if(password.getText().toString().length()==0) {
                    password.setError("Password not Entered");
                    password.requestFocus();
                    pass=false;
                }else {
                    pass = true;
                }
                if (email== true && pass==true){
                    MyDbLogin login_db=new MyDbLogin(LoginPage.this);
                    boolean valid=false;
                    ArrayList<patient> p2=login_db.get_email();
                    for(patient p3:p2)
                    {
                        String e=Email.getText().toString();
                        String p=password.getText().toString();
                        if(e.equals(p3.getKEY_email())){
                           if(p.equals(p3.getKEY_password()))
                           {
                               valid=true;
                               Toast.makeText(getApplication(),"Login Successful",Toast.LENGTH_LONG).show();
                           }else {
                               Toast.makeText(getApplication(),"Login Unsuccessful",Toast.LENGTH_LONG).show();
                           }
                      }
                    }
                    if(valid==true) {
                        DashboardActivity();
                    }
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }
    public void Register(){

        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
    public void DashboardActivity(){
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);
    }
}
