package com.example.lenovo.yijiantable;

/**
 * Description:分享弹出框
 */

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PopupShare extends PopupWindow {

    private View view;
    private Context context;
    private TextView txv_close;     //关闭弹出框
    private TextView txv_qq;        //分享qq
    private TextView txv_link;      //分享链接
    private TextView txv_wechat;    //分享微信

    public PopupShare(Context context,View.OnClickListener itemsOnClick){
        this.view= LayoutInflater.from(context).inflate(R.layout.activity_popup_share,null);

        //UI组件初始化
        txv_close=(TextView)view.findViewById(R.id.txv_close);
        txv_link=(TextView)view.findViewById(R.id.txv_link);
        txv_qq=(TextView)view.findViewById(R.id.txv_qq);
        txv_wechat=(TextView)view.findViewById(R.id.txv_wechat);

        //事件绑定
        //关闭
        txv_close.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //销毁弹出框
                dismiss();
            }
        });

        //分享的事件绑定
        txv_link.setOnClickListener(itemsOnClick);      //分享链接
        txv_qq.setOnClickListener(itemsOnClick);        //分享qq
        txv_wechat.setOnClickListener(itemsOnClick);    //分享微信

        //设置外部可点击
        this.setOutsideTouchable(true);

        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.ly_share).getTop();

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
        this.setAnimationStyle(R.style.take_popup_anim);

    }
}
