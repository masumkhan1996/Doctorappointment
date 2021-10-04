package com.example.doctorappointmentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReceptionLogin extends AppCompatActivity implements View.OnClickListener{
    DataBaseHelper sqLiteDatabase;
    EditText e1,e2;
    private Button signInButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_login);
        sqLiteDatabase = new DataBaseHelper(this);
        e1 = (EditText) findViewById(R.id.uname);
        e2 = (EditText) findViewById(R.id.pass);
        signInButton = (Button) findViewById(R.id.d3);
        signUpButton = (Button) findViewById(R.id.d4);
        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

    }
    public void onClick(View view) {
        String UserName=e1.getText().toString();
        String password=e2.getText().toString();


        if (view.getId()== R.id.d3) {
            Boolean UserNamePassword=sqLiteDatabase.chkUserNamePassword(UserName,password);
            if(UserNamePassword==true){
                Intent intent = new Intent(ReceptionLogin.this, Receptin3.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"UserName ans password did not match",Toast.LENGTH_LONG).show();

            }
        }
        else if (view.getId() == R.id.d4) {
            Intent intent = new Intent(ReceptionLogin.this,DoctorSignUp.class);
            startActivity(intent);

        }
    }
}
