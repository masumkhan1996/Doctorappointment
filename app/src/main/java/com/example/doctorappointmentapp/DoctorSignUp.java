package com.example.doctorappointmentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorSignUp extends AppCompatActivity implements View.OnClickListener {

    DataBaseHelper sqLiteDatabase;
    EditText e1,e2,e3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);
        sqLiteDatabase = new DataBaseHelper(this);

        e1 = (EditText) findViewById(R.id.name);
        e2 = (EditText) findViewById(R.id.pass);
        e3 = (EditText) findViewById(R.id.cpass);

        b1 = (Button) findViewById(R.id.register);
        b1.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        String s1=e1.getText().toString();
        String s2=e2.getText().toString();
        String s3=e3.getText().toString();

        if (s1.equals("")||s2.equals("")||s3.equals("")){
            Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
        }
        else{
            if(s2.equals(s3)){
                Boolean chkUserName=sqLiteDatabase.chkUserName(s1);
                if (chkUserName==true){
                    Boolean insert=sqLiteDatabase.insert(s1,s2);
                    if(insert==true){
                        Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"This username is already taken. Please choose another name. ",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
