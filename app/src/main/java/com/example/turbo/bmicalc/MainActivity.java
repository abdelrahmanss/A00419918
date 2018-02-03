package com.example.turbo.bmicalc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BMICALCDATABASE helper = new BMICALCDATABASE(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        // run a query
        Cursor cursor = db.query(BMICALCDATABASE.TABLE_NAME, new String[]
                        {"NAME", "PASSWORD", "DATE"},
                null, null, null, null, null); //

        if(cursor.moveToFirst())

        {
            String name = cursor.getString(0);
            EditText results = (EditText) findViewById(R.id.editText);
            results.setText(name);
        }
        cursor.close();  // cleanup
        db.close();      // cleanup
    }

    public void onClickEvent (View view) {
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }

}
