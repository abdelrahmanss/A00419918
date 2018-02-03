package com.example.turbo.bmicalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);




    }

    public void saveResult(View view){
        // gets the height
        EditText height = (EditText) findViewById(R.id.editText6);
        String heightval = height.getText().toString();
        Double heightAsInt= Double.parseDouble(heightval);
        System.out.println("Here is the height "+heightAsInt);
// Repeat for weight
        EditText weight = (EditText) findViewById(R.id.editText7);
        String weightval = height.getText().toString();
        Double weightAsInt= Double.parseDouble(weightval);
        System.out.println("Here is the height "+heightAsInt);

        Double calc= (weightAsInt/ (heightAsInt* heightAsInt));
        EditText result = (EditText)
                findViewById(R.id.editText8);
// use DecimalFormat("0.##") to limit to 2 decimal places
        result.setText(Double.toString(calc));
    }
}
