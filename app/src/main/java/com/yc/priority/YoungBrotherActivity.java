package com.yc.priority;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.robodemo.R;
import com.yc.bean.PriorityEvent;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by admin on 2014/10/9.
 */
public class YoungBrotherActivity extends Activity implements View.OnClickListener {
    Button button1,button2,button3,button4;
    TextView content;
    int mylevel=1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.young_son);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        content= (TextView) findViewById(R.id.content);

        content = (TextView) findViewById(R.id.content);
        EventBus.getDefault().register(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }


    public void onEvent(PriorityEvent event){
        append(event.txt);
        if(event.level==mylevel) EventBus.getDefault().cancelEventDelivery(this);

    }
    public void append(String str){
        content.append(str + "\n");
    }


    @Override
    public void onClick(View v) {
        //发送事件
        switch(v.getId())
        {
            case R.id.button1:
                EventBus.getDefault().post(new PriorityEvent("机密",1));
                break;
            case R.id.button2:
                EventBus.getDefault().post(new PriorityEvent("秘密",2));

                break;
            case R.id.button3:
                //测试SecondActivity中发送事件，MainActivity接收
                Intent intent=new Intent(getApplicationContext(), OldBrotherActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.button4:
                EventBus.getDefault().post(new PriorityEvent("公开",3));
                break;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        //解注册
        EventBus.getDefault().unregister(this);
    }
}