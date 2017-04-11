package com.example.lenovo.yijiantable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

/**
 * Created by lenovo on 2017/4/11.
 */

public class CountNum extends Activity implements NumberPicker.OnScrollListener{
    NumberPicker Count_p;
    Button Count_ok_b,Count_cancel_b;
    int PeopleNum = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydialog);
        Count_ok_b=(Button)findViewById(R.id.count_ok_b);
        Count_cancel_b = (Button)findViewById(R.id.count_cancel_b);
        Count_p = (NumberPicker)findViewById(R.id.count_p);

        Count_p.setMinValue(1);
        Count_p.setMaxValue(1000);
        Count_p.setValue(PeopleNum);
        Count_p.setOnScrollListener(this);
        Count_p.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                PeopleNum = newVal;
            }

        });
    }
    public void onScrollStateChange(NumberPicker view, int scrollState) {
        switch (scrollState) {
            case NumberPicker.OnScrollListener.SCROLL_STATE_FLING:
                Toast.makeText(this, "scroll state fling", Toast.LENGTH_LONG)
                        .show();
                break;
            case NumberPicker.OnScrollListener.SCROLL_STATE_IDLE:
                Toast.makeText(this, "scroll state idle", Toast.LENGTH_LONG).show();
                break;
            case NumberPicker.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                Toast.makeText(this, "scroll state touch scroll", Toast.LENGTH_LONG)
                        .show();
                break;
        }
    }
    /*public String format(int value) {
        String tmpStr = String.valueOf(value);
        if (value < 10) {
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }*/
}
