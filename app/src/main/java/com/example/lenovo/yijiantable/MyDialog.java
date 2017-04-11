package com.example.lenovo.yijiantable;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by lenovo on 2017/4/11.
 */

public class MyDialog extends Dialog {
    public MyDialog(Context context) {
        this(context,R.style.my_dialog);
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
        //加载布局并给布局的控件设置点击事件
        //View contentView = getLayoutInflater().inflate(R.layout.dialog_custom3, null);
        //super.setContentView(contentView);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 预先设置Dialog的一些属性
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        getWindow().setAttributes(p);
        p.height = (int) (d.getHeight() * 0.6);
        p.width = d.getWidth();
        p.gravity = Gravity.CENTER | Gravity.BOTTOM;
        dialogWindow.setAttributes(p);
    }
}
