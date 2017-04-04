package com.example.lenovo.yijiantable;

import android.provider.ContactsContract;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CreateHeader extends AppCompatActivity {
    FileInfo fileInfo;
    String[] Header = new String[20];
    LinearLayout layout1;
    ScrollView mScroll;
    ImageButton modify_b;
    ImageButton ok_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_header);
        layout1 = (LinearLayout)findViewById(R.id.liner);
        mScroll = (ScrollView)findViewById(R.id.ScrollV);
        modify_b = (ImageButton)findViewById(R.id.xiugai);
        ok_b = (ImageButton)findViewById(R.id.ok);
        mScroll.smoothScrollTo(0,0);
        Header[0] = "名字";
        Header[1] = "名字";
        Header[2] = "名字";
        Header[3] = "名字";
        Header[4] = "名字";
        Header[5] = "名字";
        Header[6] = "名字";
        Header[7] = "名字";
        Header[8] = "名字";
       // fileInfo = new FileInfo(Header);
        //Header = fileInfo.GetHeader();
        for(int i = 0;i<8;i++) {
            EditText text = new EditText(this);
            text.setBackground(getResources().getDrawable(R.drawable.edittext,null));
            text.setText(Header[i]);
            text.setGravity(Gravity.CENTER);
            text.setEnabled(false);
            text.setTextColor(getResources().getColor(R.color.Black));
            layout1.addView(text);
        }
    }
}
