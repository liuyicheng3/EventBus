package com.yc.bean;

public class UIThreadEvent {
    private String text;
    public UIThreadEvent(String text)
    {
        this.text=text;
    }
    public String getText(){
        return text;
    }
}
