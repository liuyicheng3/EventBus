package com.yc.bean;

/**
 * Created by admin on 2014/10/9.
 */
public class PriorityEvent {

    public String txt;
    public int level=10;

    public PriorityEvent(String txt,int i) {
        this.txt = txt;
        this.level=i;
    }
}
