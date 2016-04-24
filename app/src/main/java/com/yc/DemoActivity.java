package com.yc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.robodemo.R;
import com.yc.basic.BasicActivity;
import com.yc.priority.ParentActivity;

public class DemoActivity extends Activity implements View.OnClickListener{
    Button button1,button2,button3,button4;
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        content= (TextView) findViewById(R.id.content);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    public void append(String str){
       content.append(str+"\n");
    }

    @Override
    public void onClick(View v) {
       //发送事件
        switch(v.getId())
        {
            case R.id.button1:
                startActivity(new Intent(this,BasicActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this,ParentActivity.class));

                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
    }
}
