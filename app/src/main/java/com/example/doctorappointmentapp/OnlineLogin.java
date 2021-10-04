package com.example.doctorappointmentapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OnlineLogin extends AppCompatActivity implements View.OnClickListener {
    MyDatabaseHelper myDatabaseHelper;
    private EditText nameEditText,addressEditText,diseaseEditText,numberEditText,genderEdiText,idEditText;
    private Button cButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_login);
        myDatabaseHelper= new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase= myDatabaseHelper.getWritableDatabase();
        nameEditText=(EditText)findViewById(R.id.name);
        addressEditText=(EditText)findViewById(R.id.address);
        diseaseEditText=(EditText)findViewById(R.id.disease);
        numberEditText=(EditText)findViewById(R.id.number);
        genderEdiText=(EditText)findViewById(R.id.gender);
        idEditText=(EditText)findViewById(R.id.id);
        cButton=(Button)findViewById(R.id.c);
        cButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String name = nameEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String disease = diseaseEditText.getText().toString();
        String phoneNumber = numberEditText.getText().toString();
        String gender = genderEdiText.getText().toString();
        String id= idEditText.getText().toString();

        if (view.getId() == R.id.c) {
            long rowId = myDatabaseHelper.insertData(name, address, disease, phoneNumber, gender);
            if (rowId == -1) {
                Toast.makeText(getApplicationContext(), " Unsuccessful", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Row " + rowId + " is successfully insert", Toast.LENGTH_LONG).show();
            }

        }
        if(view.getId()==R.id.c1) {
            Cursor cursor = myDatabaseHelper.displaydata();
            if (cursor.getCount() == 0) {
                showData("Error", "No Data Found");
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append("Serial number: " + cursor.getString(0) + "\n\n");
                stringBuffer.append("Name: " + cursor.getString(1) + "\n\n");
               // stringBuffer.append("Address: " + cursor.getString(2) + "\n\n");
               // stringBuffer.append("Disease: " + cursor.getString(3) + "\n\n");
               // stringBuffer.append("PhoneNumber: " + cursor.getString(4) + "\n\n");
                //stringBuffer.append("Gender: " + cursor.getString(5) + "\n\n");
            }
            showData("Patient Details",stringBuffer.toString());
        }
    }
    private void showData(String title, String resultSet) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(resultSet);
        builder.setCancelable(true);
        builder.show();
    }
}


