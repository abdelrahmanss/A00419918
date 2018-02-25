package com.example.turbo.bmicalc;

import android.app.DatePickerDialog;
import android.app.AlertDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Main3Activity extends AppCompatActivity {
    BMICALCDATABASE mydb;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat simpleDateFormat;
    EditText height_txt, weight_txt, result_txt, daterecord;
    Button showrecords;
    Button addrecord;
    Button clearbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mydb = new BMICALCDATABASE(this);


        height_txt = (EditText) findViewById(R.id.editText6);
        weight_txt = (EditText) findViewById(R.id.editText7);
        result_txt = (EditText) findViewById(R.id.editText8);
        daterecord = (EditText) findViewById(R.id.daterecord);
        showrecords = (Button) findViewById(R.id.showrecords);    //Just added
        addrecord = (Button) findViewById(R.id.AddRecord);    //Just added
        clearbtn=(Button) findViewById(R.id.clearbtn) ;
        setDateTimeField();
        AddData();
        viewAll();
        clearallrecords();
        daterecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    }


    private void setDateTimeField() {
        Calendar calendar = Calendar.getInstance();
        daterecord.setFocusable(false);
        daterecord.setClickable(true);

        datePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(i, i1, i2);
                daterecord.setText(simpleDateFormat.format(calendar1.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }


    public void calcResult(View view) {
        // gets the height
        String heightval = height_txt.getText().toString();
        Double heightAsInt = Double.parseDouble(heightval);
        System.out.println("Here is the height " + heightval);
// Repeat for weight
        String weightval = weight_txt.getText().toString();
        Double weightAsInt = Double.parseDouble(weightval);
        System.out.println("Here is the weight " + weightval);
        if (heightAsInt < 0.5 || heightAsInt > 2.5 || weightAsInt < 10 || weightAsInt > 250) {
            showMessage("Error", "Height should be in range of 0.5 to 2.5 metre & weight 10 to 250 KG");
        } else {
            Double calc = (weightAsInt / (heightAsInt * heightAsInt));
// use DecimalFormat("0.##") to limit to 2 decimal places
            result_txt.setText(Double.toString(calc));
        }
    }


    public void AddData() {
        addrecord.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.InsertBMI(height_txt.getText().toString(),
                                weight_txt.getText().toString(),
                                result_txt.getText().toString(),
                                daterecord.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(Main3Activity.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main3Activity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    public void viewAll() {

        showrecords.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = mydb.getalldata();
                        if (res.getCount() == 0) {
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("_id :" + res.getString(0) + "\n");
                            buffer.append("height :" + res.getString(1) + "\n");
                            buffer.append("_weight :" + res.getString(2) + "\n");
                            buffer.append("_bmi :" + res.getString(3) + "\n");
                            buffer.append("_DATE :" + res.getString(4) + "\n");
                            buffer.append("------------------------------------" + "\n");
                        }
                        showMessage("BMI Records", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void clearallrecords() {

        clearbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mydb.clearallrecords();
                        Toast.makeText(Main3Activity.this, "All recorded data has been cleared", Toast.LENGTH_LONG).show();

                        };


                }
        );
    }

    public void onClickEventlogout (View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}



