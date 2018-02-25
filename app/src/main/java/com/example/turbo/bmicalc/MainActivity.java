package com.example.turbo.bmicalc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BMICALCDATABASE helper;

    EditText EmailField,PasswordField;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new BMICALCDATABASE(this);


        PasswordField= (EditText) findViewById(R.id.editText5);
        EmailField= (EditText) findViewById(R.id.Email);




        SQLiteDatabase db = helper.getWritableDatabase();
        // run a query
        Cursor cursor = db.query(BMICALCDATABASE.TABLE_NAME, new String[]
                        { "EMAIL","PASSWORD"},
                null, null, null, null, null); //

        if(cursor.moveToFirst())

        {


            String email = cursor.getString(0);
            EmailField.setText(email);
            password = cursor.getString(1);
         //   PasswordField.setText(password);

        }
        cursor.close();  // cleanup
        db.close();      // cleanup
    }

    public void onClickEvent (View view)
    {


        EditText pwd = (EditText) findViewById(R.id.editText5);

        String pass=pwd.getText().toString();

        if ( pass.equals(password)    ) {
            Intent intent = new Intent(this, Main3Activity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(MainActivity.this, "Password is Incorrect", Toast.LENGTH_LONG).show();

        }



    }


    public void onClickEventreg (View view) {

        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }



}
