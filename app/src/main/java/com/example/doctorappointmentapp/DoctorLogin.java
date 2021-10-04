package com.example.doctorappointmentapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorLogin extends AppCompatActivity  implements View.OnClickListener {
    DataBaseHelper sqLiteDatabase;
    EditText e1,e2;
    private Button signInButton, signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        sqLiteDatabase = new DataBaseHelper(this);

        e1 = (EditText) findViewById(R.id.u);
        e2 = (EditText) findViewById(R.id.pp);

        signInButton = (Button) findViewById(R.id.d1);
        signUpButton = (Button) findViewById(R.id.d2);

        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

    }

    public void onClick(View view) {
        String UserName=e1.getText().toString();
        String password=e2.getText().toString();

        if (view.getId()== R.id.d1) {
            Boolean UserNamePassword=sqLiteDatabase.chkUserNamePassword(UserName,password);
            if(UserNamePassword==true){
                Intent intent = new Intent(DoctorLogin.this, ViewAppointment.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"UserName ans password did not match",Toast.LENGTH_LONG).show();

            }
        }
        else if (view.getId() == R.id.d2) {
            Intent intent = new Intent(DoctorLogin.this,DoctorSignUp.class);
            startActivity(intent);

        }
    }
}