package com.example.lenovo.yijiantable;

import android.app.Dialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.DrawableRes;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CreateHeader extends AppCompatActivity {
    FileInfo fileInfo;
    String[] Header= new String[9];
    EditText[] edittexts;
    CheckBox[] checkBoxes;
    TableLayout tableLayout;
    ScrollView mScroll;
    TextView modify_ok;
    EditText date_b,count_num;
    ImageButton modify_b,next_b,ok_b;
    Dialog mDateDialog;
    Window dialogWindow;
    NumberPicker Count_p,Month_p,Day_p,Hour_p,Minute_p;
    Button Count_ok_b,Count_cancel_b,Date_ok_b,Date_cancel_b;
    boolean bk_flag = true;
    int PeopleNum = 1;
    int Month,Day,Hour,Minute;

    private View.OnTouchListener touchListener = new MyOnTouchListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_header);

        tableLayout = (TableLayout)findViewById(R.id.table);
        mScroll = (ScrollView)findViewById(R.id.ScrollV);
        next_b = (ImageButton)findViewById(R.id.next);
        modify_b = (ImageButton)findViewById(R.id.xiugai);
        modify_ok = (TextView)findViewById(R.id.modify_ok);
        date_b = (EditText)findViewById(R.id.date);
        count_num = (EditText)findViewById(R.id.countNum_text);



        mScroll.smoothScrollTo(0,0);
        Header[0] = "名字";
        Header[1] = "名字";
        Header[2] = "名字";
        Header[3] = "专业";
        Header[4] = "名字";
        Header[5] = "名字";
        Header[6] = "名字";
        Header[7] = "名字";
        Header[8] = "名字";
       // fileInfo = new FileInfo(Header);
        //Header = fileInfo.GetHeader();
        edittexts = new EditText[Header.length];
        checkBoxes = new CheckBox[Header.length];
        for(int i = 0;i<Header.length;i++) {          //显示表头数据
            TableRow row = new TableRow(this);
            checkBoxes[i] = new CheckBox(this);
            checkBoxes[i].setEnabled(false);          //不可操作
            checkBoxes[i].setChecked(true);

            edittexts[i] = new EditText(this);        //初始化
            edittexts[i].setTextSize(16);

            edittexts[i].setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            edittexts[i].setSingleLine(false);
            edittexts[i].setGravity(Gravity.TOP);
            edittexts[i].setHorizontallyScrolling(false);       //文本框自动换行未实现

            edittexts[i].setText(Header[i]);
            edittexts[i].setGravity(Gravity.START);
            edittexts[i].setEnabled(false);
            edittexts[i].setTextColor(getResources().getColor(R.color.mainColorPink));

            row.addView(checkBoxes[i]);
            row.addView(edittexts[i]);
            tableLayout.addView(row);
        }

        modify_b.setOnTouchListener(touchListener);     //设置修改按钮的点击后变化
        next_b.setOnTouchListener(touchListener);

        modify_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {             //设置修改按钮点击后表的变化
                if(!bk_flag){  //修改状态变为确定状态
                    modify_b.setBackground(getResources().getDrawable(R.mipmap.ch_xiugai));
                    modify_ok.setText("修改");
                    bk_flag = true;
                    for(int i = 0;i<Header.length;i++){
                        edittexts[i].clearFocus();
                        edittexts[i].setEnabled(false);
                        // checkBoxes[i].setBackgroundColor(getResources().getColor(R.color.mainColorLGrey));
                        checkBoxes[i].setEnabled(false);
                        edittexts[i].setTextColor(getResources().getColor(R.color.mainColorPink));
                    }
                }
                else{      //确定状态变为修改状态
                    modify_b.setBackground(getResources().getDrawable(R.mipmap.ch_ok));
                    modify_ok.setText("确定");
                    bk_flag = false;
                    for(int i = 0;i<Header.length;i++) {
                        edittexts[i].setEnabled(true);
                        edittexts[i].setTextColor(getResources().getColor(R.color.Black));
                        checkBoxes[i].setEnabled(true);

                    }
                }
            }
        });

      /*  next_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateHeader.this,Requirements.class);
                startActivity(intent);
            }
        });*/

        mDateDialog = new Dialog(this,R.style.my_dialog);
        dialogWindow = mDateDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画



        date_b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                CreateDialog(R.layout.activity_mydialog).show();

                return true;
            }
        });
        count_num.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                CreateDialog(R.layout.count_num).show();
                Count_p = (NumberPicker)mDateDialog.findViewById(R.id.count_p);
                Count_p.setMinValue(1);
                Count_p.setMaxValue(1000);
                Count_p.setValue(PeopleNum);
                Count_p.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        PeopleNum = newVal;
                    }
                });

                Count_ok_b = (Button)mDateDialog.findViewById(R.id.count_ok_b);
                Count_ok_b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        count_num.setText(" "+String.valueOf(PeopleNum));
                        mDateDialog.cancel();
                    }
                });
                Count_cancel_b = (Button)mDateDialog.findViewById(R.id.count_cancel_b);
                Count_cancel_b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDateDialog.cancel();
                    }
                });

                return true;
            }
        });

        date_b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                CreateDialog(R.layout.activity_mydialog).show();
                Month_p = (NumberPicker)mDateDialog.findViewById(R.id.month_p);
                Day_p = (NumberPicker)mDateDialog.findViewById(R.id.day_p);
                Hour_p = (NumberPicker)mDateDialog.findViewById(R.id.hour_p);
                Minute_p = (NumberPicker)mDateDialog.findViewById(R.id.minute_p);
                init(Month_p);
                init(Day_p);
                init(Hour_p);
                init(Minute_p);
                Month_p.setOnValueChangedListener(new MyValueChangeListener());
                Day_p.setOnValueChangedListener(new MyValueChangeListener());
                Hour_p.setOnValueChangedListener(new MyValueChangeListener());
                Minute_p.setOnValueChangedListener(new MyValueChangeListener());

                Date_ok_b = (Button)mDateDialog.findViewById(R.id.setTime_ok_b);
                Date_ok_b .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        date_b.setText(String.valueOf(Month+"月"+Day+"日"+Hour+":"+Minute));
                        mDateDialog.cancel();
                    }
                });
                Date_cancel_b = (Button)mDateDialog.findViewById(R.id.setTime_cancel_b);
                Date_cancel_b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDateDialog.cancel();
                    }
                });

                return true;
            }
        });

    }

    private Dialog CreateDialog(int layout){
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(layout,null);
        mDateDialog.setContentView(root);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        return mDateDialog;
    }

    private void init(NumberPicker picker){
        int id = picker.getId();
        switch (id){
            case R.id.month_p:
                picker.setMinValue(1);
                picker.setMaxValue(12);
                picker.setValue(1);
                picker.setFormatter(new MyFormat());
                break;
            case R.id.day_p:
                picker.setMinValue(1);
                picker.setMaxValue(31);
                picker.setValue(1);
                break;
            case R.id.hour_p:
                picker.setMinValue(1);
                picker.setMaxValue(24);
                picker.setValue(1);
                break;
            case R.id.minute_p:
                picker.setMinValue(00);
                picker.setMaxValue(60);
                picker.setValue(01);
                picker.setFormatter(new MyFormat());
                break;
        }
    }

    protected class MyValueChangeListener implements NumberPicker.OnValueChangeListener{
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            int id = picker.getId();
            switch (id){
                case R.id.month_p:
                    Month = newVal;
                    break;
                case R.id.day_p:
                    Day = newVal;
                    break;
                case R.id.hour_p:
                    Hour = newVal;
                    break;
                case R.id.minute_p:
                    Minute = newVal;
                    break;
            }
        }
    }

    private class MyFormat implements NumberPicker.Formatter{
        public String format(int value) {
            String tmpStr = String.valueOf(value);
            if (value < 10) {
                tmpStr = "0" + tmpStr;
            }
            return tmpStr;
        }
    }

}
