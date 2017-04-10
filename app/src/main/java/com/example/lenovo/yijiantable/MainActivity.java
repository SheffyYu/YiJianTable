package com.example.lenovo.yijiantable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import layout.FragmentContent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //UI object
    private TextView txv_home;      //底部导航栏主页键
    private TextView txv_add;       //底部导航栏添加键
    private TextView txv_user;      //底部导航栏用户键
    private TextView txv_select;    //上部导航栏筛选键
    private TextView txv_search;    //上部导航栏搜索键
    private Toolbar toolbar;        //标题栏
    private FrameLayout fl_home;    //中间部分的FrameLayout容器

    private PopupHeader popWin;     //提取表头和创建表头的弹出框

    //Fragment object
    private FragmentContent fg_home, fg_user;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getSupportFragmentManager();
        bindViews();                    //UI组件初始化与事件绑定
        //txv_home.performClick();    //模拟一次点击，进入home界面
        txv_home.setSelected(true);
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        txv_home = (TextView) findViewById(R.id.txv_home);
        txv_add = (TextView) findViewById(R.id.txv_add);
        txv_user = (TextView) findViewById(R.id.txv_user);
        txv_search = (TextView) findViewById(R.id.txv_search);
        txv_select = (TextView) findViewById(R.id.txv_select);
        fl_home = (FrameLayout) findViewById(R.id.fl_home);

        txv_home.setOnClickListener(this);
        txv_user.setOnClickListener(this);
        txv_add.setOnClickListener(this);
        txv_select.setOnClickListener(this);
        txv_search.setOnClickListener(this);
    }

    //重置所有文本选中状态
    private void setSelected() {
        txv_home.setSelected(true);
        txv_user.setSelected(false);
        txv_add.setSelected(false);
        txv_search.setSelected(false);
        txv_select.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
//        if (fg_home != null) {
//            fragmentTransaction.hide(fg_home);
//        }
        if (fg_user != null) {
            fragmentTransaction.hide(fg_user);
        }
    }

    //设置 创建表头与提取表头按钮的弹出窗口 的弹出方法
    public void showPopFormBottom(View view) {
        PopupHeader popWin = new PopupHeader(this, itemsOnClick);
//        设置Popupwindow显示位置（从底部弹出）
        popWin.showAtLocation(findViewById(R.id.activity_main), Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0);

        //当弹出Popupwindow时，背景变半透明
        getWindow().getAttributes().alpha=0.7f;
        getWindow().setAttributes(getWindow().getAttributes());
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
        popWin.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
               // params = getWindow().getAttributes();
                getWindow().getAttributes().alpha=1f;
                getWindow().setAttributes(getWindow().getAttributes());
            }
        });
    }

    //为 创建表头与提取表头按钮的弹出窗口 实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            popWin.dismiss();
            switch (v.getId()) {
                case R.id.txv_createheader:
                    break;
                case R.id.txv_pickheader:
                    break;
                default:
                    break;
            }
        }
    };



    @Override
    //点击事件
    public void onClick(View v) {
        FragmentTransaction fgTransaction = fManager.beginTransaction();
        hideAllFragment(fgTransaction);
        switch (v.getId()) {
            case R.id.txv_home:         //主页，直接显示FrameLayout
                setSelected();
                txv_home.setSelected(true);
//                if (fg_home == null) {
//                    fg_home = new FragmentContent();                  //若不存在，则新建一个
//                    fgTransaction.add(R.id.fl_home, fg_home);        //将fragment添加到FrameLayout容器中
//                } else {
//                    fgTransaction.show(fg_home);
//                }
                break;
            case R.id.txv_user:         //用户按钮的点击事件，添加一个Fragment
                setSelected();
                txv_home.setSelected(false);
                txv_user.setSelected(true);
                if (fg_user == null) {
                    fg_user = new FragmentContent();                  //若不存在，则新建一个
                    fgTransaction.add(R.id.fl_home, fg_user);        //将fragment添加到FrameLayout容器中
                } else {
                    fgTransaction.show(fg_user);
                }
                break;
            case R.id.txv_add:          //添加按钮的点击事件
                setSelected();
                txv_add.setSelected(true);
                showPopFormBottom(v);
                break;
        }
        fgTransaction.commit();
    }

}

