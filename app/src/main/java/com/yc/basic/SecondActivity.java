package com.yc.basic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.robodemo.R;
import com.yc.bean.UIThreadEvent;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends Activity implements View.OnClickListener {
    Button button1,button2,button21,button22;
    TextView content;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button1 = (Button) findViewById(R.id.button1);
        content = (TextView) findViewById(R.id.content);
        button2 = (Button) findViewById(R.id.button2);
        button21 = (Button) findViewById(R.id.button21);
        button22 = (Button) findViewById(R.id.button22);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button21.setOnClickListener(this);
        button22.setOnClickListener(this);
//        EventBus.getDefault().register(this, "appendText", UIThreadEvent.class);
        EventBus.getDefault().register(this);


    }

    public void onUIThreadEvent(UIThreadEvent event)
    {
        append(event.getText());
    }
    public void appendTextMainThread(UIThreadEvent event)
    {
        append(event.getText());
    }

    //默认的是postthread  线程  这时候中log中会抛出异常
/*    public void appendText(UIThreadEvent event)
    {
        append(event.getText());
    }*/
    public void append(String str){
        content.append(str+"\n");
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.button1:
//                finish();
                startActivity(new Intent(this,ThirdActivity.class));
                break;
            case R.id.button2:
                EventBus.getDefault().post(new UIThreadEvent("Message From SecondActivity"));
                break;
            case R.id.button21:
                //postSticky会缓存最新的event事件，不管接收方是否消亡，载入时都会检测最新状态
                EventBus.getDefault().postSticky(new UIThreadEvent("Sticky Message From SecondActivity"));
                break;
            case R.id.button22:
                //postSticky会缓存最新的event事件，不管接收方是否消亡，载入时都会检测最新状态
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        EventBus.getDefault().post(new UIThreadEvent("Message From NoneUI SecondActivity"));
                    }
                }.start();
                break;
        }
    }
}
