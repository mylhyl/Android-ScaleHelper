package com.mylhyl.scalehelper.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private RandomCodeView mRandomCodeView, mRandomCodeView1;
    private Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int densityDpi = metric.densityDpi;
        float density = metric.density;

        Log.d(TAG, "densityDpi:" + densityDpi + " density: " + density);

        mRandomCodeView = (RandomCodeView) findViewById(R.id.view);
        mRandomCodeView1 = (RandomCodeView) findViewById(R.id.view1);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

        mRandomCodeView.setText("1234");
        mRandomCodeView1.setText("2345");

        mRandomCodeView.setOnClickListener(this);
        mRandomCodeView1.setOnClickListener(this);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button || v.getId() == R.id.button2) {
            int size = Integer.valueOf(((Button) v).getText().toString());
            mRandomCodeView.setTextSize(size);
            mRandomCodeView1.setTextSize(size);
        } else {
            Random random = new Random();
            int randomInt = random.nextInt(99999);
            mRandomCodeView.setText(String.valueOf(randomInt));
            mRandomCodeView1.setText(String.valueOf(randomInt));
        }
    }
}
