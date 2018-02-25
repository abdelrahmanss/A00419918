package com.example.turbo.bmicalc;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import static com.example.turbo.bmicalc.R.layout.updatepass;

/**
 * Created by TURBO on 2018-02-24.
 */

public class Updatepass extends AppCompatActivity {

    BMICALCDATABASE mydb;
    Button showuserrecord;
    Button updateuserpass;
    EditText userid_txt,name_txt,pass_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(updatepass);
        mydb = new BMICALCDATABASE(this);

        showuserrecord = (Button) findViewById(R.id.button4);
        updateuserpass = (Button) findViewById(R.id.button3) ;

        userid_txt = (EditText) findViewById(R.id.editText2);
        name_txt = (EditText) findViewById(R.id.name);
        pass_txt = (EditText) findViewById(R.id.pass);
        viewAll();
        UpdateData();

    }

    public void UpdateData() {
        updateuserpass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = mydb.updateData(userid_txt.getText().toString(),
                                pass_txt.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(Updatepass.this,"Password Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Updatepass.this,"Password not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {

        showuserrecord.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = mydb.getallusers();
                        if (res.getCount() == 0) {
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("_id :" + res.getString(0) + "\n");
                            buffer.append("Name :" + res.getString(1) + "\n");
                            buffer.append("Email :" + res.getString(2) + "\n");
                            buffer.append("Password :" + res.getString(3) + "\n");
                            buffer.append("Health Card Number :" + res.getString(4) + "\n");
                            buffer.append("------------------------------------" + "\n");
                        }
                        showMessage("User Records",buffer.toString());
                    }
                }
        );
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
