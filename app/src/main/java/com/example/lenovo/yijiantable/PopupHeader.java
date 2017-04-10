package com.example.lenovo.yijiantable;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PopupHeader extends PopupWindow {

    private View view;
    private Context context;
    private TextView txv_close;         //关闭弹出框
    private TextView txv_pickheader;    //提取表头
    private TextView txv_createheader;  //创建表头



    public PopupHeader(Context context,View.OnClickListener itemsOnClick){
        this.view= LayoutInflater.from(context).inflate(R.layout.activity_popup_header,null);

        //UI组件初始化
        txv_close=(TextView)view.findViewById(R.id.txv_close);
        txv_createheader=(TextView)view.findViewById(R.id.txv_createheader);
        txv_pickheader=(TextView)view.findViewById(R.id.txv_pickheader);

        //事件绑定
        //关闭按钮
        txv_close.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //销毁弹出框
                dismiss();
            }
        });

        txv_pickheader.setOnClickListener(itemsOnClick);        //提取表头
        txv_createheader.setOnClickListener(itemsOnClick);      //创建表头

        //设置外部可点击
        this.setOutsideTouchable(true);

        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择外面则销毁弹出框
        this.view.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.activity_popup_header).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

         /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);

    }

}
