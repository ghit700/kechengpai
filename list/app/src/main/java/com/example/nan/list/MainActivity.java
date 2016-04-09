package com.example.nan.list;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView list;
    Context context;
    List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        items=new ArrayList<>();
        Item item = new Item();
        List<String> lists = new ArrayList<String>();
        for (int i = 0; i < 5; ++i) {
            item.setTime("111" + i);
            for (int j = 0; j < 3; ++j) {
                lists.add("111" + j);
            }
            item.setTexts(lists);
            items.add(item);
        }

        list.setAdapter(new MyAdapter(context,items));

    }
}
