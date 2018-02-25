package com.example.turbo.bmicalc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    BMICALCDATABASE helper;

    EditText nameField,DOBField,HCNField,EmailField,PasswordField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        helper = new BMICALCDATABASE(this);

        nameField = (EditText) findViewById(R.id.editText);
        DOBField = (EditText) findViewById(R.id.editText3);
        HCNField= (EditText) findViewById(R.id.editText4);
        PasswordField= (EditText) findViewById(R.id.editText5);
        EmailField= (EditText) findViewById(R.id.Email);

        SQLiteDatabase db = helper.getWritableDatabase();
        // run a query
        Cursor cursor = db.query(BMICALCDATABASE.TABLE_NAME, new String[]
                        {"NAME", "EMAIL","PASSWORD","HEALTH_CARD_NUMB","DOB"},
                null, null, null, null, null); //

        if(cursor.moveToFirst())

        {
            String name = cursor.getString(0);
            nameField.setText(name);
            String email = cursor.getString(1);
            EmailField.setText(email);
            String password = cursor.getString(2);
            PasswordField.setText(password);
            String hcn = cursor.getString(3);
            HCNField.setText(hcn);
            String dob = cursor.getString(4);
            DOBField.setText(dob);
        }
        cursor.close();  // cleanup
        db.close();      // cleanup
    }

    public void onClickEvent (View view)
    {
        EditText uname = (EditText) findViewById(R.id.editText);
        EditText email = (EditText) findViewById(R.id.Email);
        EditText pwd = (EditText) findViewById(R.id.editText5);
        EditText HCN = (EditText) findViewById(R.id.editText4);

        final String name=uname.getText().toString();
        final String pass=pwd.getText().toString();
        final String hcn=HCN.getText().toString();
        final String mail= email.getText().toString();
        //Validating the name field so it's not blank
        if(name.length()==0)
        {
            uname.requestFocus();
            uname.setError("Name cannot be blank");
        }
        // Validate name to make sure it contain characters
        else if(!name.matches("[a-zA-Z ]+"))
        {
            uname.requestFocus();
            uname.setError("Please enter only Alphabetic Character for the Name");
        }

        //Validate the password is not blank
        else if (pass.length()==0)
        {
            pwd.requestFocus();
            pwd.setError("Password can't be Blank");
        }
        else if (hcn.length()==0)
        {
            pwd.requestFocus();
            pwd.setError("Health Card Number cannot be Blank");
        }
        else if (!mail.matches("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"))
        {
            pwd.requestFocus();
            pwd.setError("Email format is not valid");
        }
        else
        {
            BMICALCDATABASE helper = new BMICALCDATABASE(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            helper.clearallusers();  // Deleting all users prior of creating a new user (Single User App!)
            helper.InsertUserDetails( nameField.getText().toString(),PasswordField.getText().toString(), HCNField.getText().toString(), DOBField.getText().toString(),EmailField.getText().toString());
            db.close();
            Intent intent = new Intent(this, Main3Activity.class);
            Toast.makeText(register.this, "User has been created successfully", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }

    public void onClickEvent2 (View view) {

        Intent intent = new Intent(this, Updatepass.class);
        startActivity(intent);
    }


}
