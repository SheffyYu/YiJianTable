package com.example.lenovo.yijiantable;

import android.graphics.ColorMatrixColorFilter;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lenovo on 2017/4/6.
 */

public class MyOnTouchListener implements View.OnTouchListener {
    public final float[] BT_PRESSED =
            new float[]{1.5f,0,0,0,2,
                    0,2,0,0,2,
                    0,0,1.5f,0,1.5f,
                    0,0,0,1,0};
    public final float[] BT_RELEASE =
            new float[]{1,0,0,0,0,
                    0,1,0,0,0,
                    0,0,1,0,0,
                    0,0,0,1,0};
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_PRESSED));
            v.setBackgroundDrawable(v.getBackground());
          //  v.setBackground(v.getBackground());
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_RELEASE));
            v.setBackgroundDrawable(v.getBackground());
        }
        return false;
    }
   /* public void setMyOnTouchListener(View v){

    }*/
}
