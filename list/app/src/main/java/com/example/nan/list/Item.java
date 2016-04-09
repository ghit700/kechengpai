package com.example.nan.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nan on 2016/4/9.
 */
public class Item {

    private String time;
    private List<String> texts;


    public String getTime() {
        return time;
    }

    public List<String> getTexts() {
        return texts;
    }

    public int getCount() {
        return texts == null ? 1 : 1 + texts.size();
    }

    @Override
    public boolean equals(Object o) {
        if (null != o && o instanceof Item) {
            return ((Item) o).getTime().equals(time);
        }
        return false;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    ;
}
