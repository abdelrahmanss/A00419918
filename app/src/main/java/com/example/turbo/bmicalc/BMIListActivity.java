package com.example.turbo.bmicalc;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.*;

public class BMIListActivity extends ListActivity {


    List<Double> wArray = new ArrayList<Double>();
    List<Double> hArray = new ArrayList<Double>();
    List<String> dArray = new ArrayList<String>();
    List<BMIResult> results = new ArrayList<BMIResult>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BMICALCDATABASE helper = new BMICALCDATABASE(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ListView listBMIResults = getListView();

        //db query here and put into results
        Cursor cursor = db.query(BMICALCDATABASE.TABLE_NAME2,
                new String[]{"WEIGHT","HEIGHT","DATE"},
                null,null,null,null,null);

        while(cursor.moveToNext()){
            String weight = cursor.getString(0);
            String height = cursor.getString(1);
            String date = cursor.getString(2);

            Double intW = Double.parseDouble(weight);
            Double intH = Double.parseDouble(height);

            wArray.add(intW);
            hArray.add(intH);
            dArray.add(date);

        }
        Integer[] listItems = {1,2,3,4,5};//new String[wArray.size()];

        for (int i = 0;i<wArray.size();i++) {

            results.add(new BMIResult(wArray.get(i), hArray.get(i)));
            System.out.println(results.get(i));
        }

        ArrayAdapter<BMIResult> listAdapter = new ArrayAdapter<BMIResult>(this, android.R.layout.simple_list_item_1, results);
        listBMIResults.setAdapter(listAdapter);

    }

    public void onListItemClick(ListView listView, View itemView, int position, long id){
        System.out.println("Clicked on: " + results.get(position).toString());
    }
}