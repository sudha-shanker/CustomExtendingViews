package com.example.customextendingviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    CustomViewFan customViewFan;
    CustomViewDrawing customViewDrawing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

       //  customViewFan = new CustomViewFan(this);
       // setContentView(customViewFan);

        customViewDrawing = new CustomViewDrawing(this);
        setContentView(customViewDrawing);


    }
}
