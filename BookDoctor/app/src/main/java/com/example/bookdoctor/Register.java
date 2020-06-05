package com.example.bookdoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import patient.patient;
import java.util.regex.Matcher;

import static android.text.TextUtils.isEmpty;


public class Register extends AppCompatActivity {
    EditText FName, LName,PNo, email, password, Confirm_passd;
    TextView textView;
    RelativeLayout relativeLayout;
    boolean f_name=false,l_name=false,phn_no=false,email1=false,cfn=false,pass=false,pass_cfn=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textView=findViewById(R.id.login);
        relativeLayout=findViewById(R.id.register);
        FName= findViewById(R.id.name);
        LName= findViewById(R.id.surname);
        PNo= findViewById(R.id.phoneno);
        email= findViewById(R.id.email);
        password= findViewById(R.id.Password);
        Confirm_passd= findViewById(R.id.ConfirmPassword);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginPage();
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FName.getText().toString().length()==0){
                    FName.setError("First Name is Not Entered");
                    FName.requestFocus();
                    f_name=false;
                }/*else if(!FName.matches("[a-zA-Z ]+")){
                    FName.setError("Invalid First Name");
                }*/else{

                    f_name=true;
                }
                if(LName.getText().toString().length()==0){
                    LName.setError("Last Name is not Entered");
                    LName.requestFocus();
                    l_name=false;
                }/*else if(!LName.equals("^[A-Za-z]+$")) {
                    FName.setError("Invalid Last Name");
                } */else{
                    l_name=true;
                }
                if(PNo.getText().toString().length()==0){
                    PNo.setError("Phone Number is not Entered");
                    PNo.requestFocus();
                    phn_no=false;
                }/*else if(!PNo.equals("^[7-9][0-9]{9}$")){
                    PNo.setError("Invalid Mobile Number");
                }*/ else {
                    phn_no=true;
                }
                if(email.getText().toString().length()==0){
                    email.setError("Email is not Entered");
                    email.requestFocus();
                    email1=false;
                }/*else if(!email.){
                    email.setError("Please enter a valid Email address");
                    email.requestFocus();

                }*/else{

                    email1=true;
                }
                if(password.getText().toString().length()==0) {
                    password.setError("Password not Entered");
                    password.requestFocus();
                    pass=false;
                }else{

                    pass=true;
                }
                if(Confirm_passd.getText().toString().length()==0) {
                    Confirm_passd.setError("Please Confirm Password");
                    Confirm_passd.requestFocus();
                    cfn=false;
                }else{

                    cfn=true;
                }
                if(!password.getText().toString().equals(Confirm_passd.getText().toString())){
                    password.setError("Password not Matched");
                    password.requestFocus();
                    pass_cfn=false;
                }
                else{

                        pass_cfn=true;
                }
                if (f_name==true && l_name==true && phn_no==true && email1==true && cfn==true && pass==true && pass_cfn==true){
                    MyDbPatient patientdb = new MyDbPatient(Register.this);
                    patient p1 = new patient();
                    p1.setKEY_f_name(FName.getText().toString());
                    p1.setKEY_l_name(LName.getText().toString());
                    p1.setKEY_Phn_no(PNo.getText().toString());
                    p1.setKEY_email(email.getText().toString());
                    p1.setKEY_password(password.getText().toString());
                    patientdb.insert1(p1);
                    LoginPage();

                }

            }
        });
    }

    public void LoginPage(){
        Intent intent = new Intent(this,LoginPage.class);
        startActivity(intent);
    }
    /*public void DashboardActivity(){
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);
    }*/

}

