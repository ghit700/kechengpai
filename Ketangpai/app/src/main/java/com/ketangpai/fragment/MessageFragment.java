package com.ketangpai.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.ketangpai.activity.ChatActivity;
import com.ketangpai.adapter.MessageExAdapter;
import com.ketangpai.base.BaseFragment;
import com.ketangpai.nan.ketangpai.R;

import java.util.ArrayList;

/**
 * Created by nan on 2016/3/15.
 */
public class MessageFragment extends BaseFragment implements ExpandableListView.OnChildClickListener {

    //view
    ExpandableListView mMessageExList;

    //adpter
    MessageExAdapter mMessageExAdapter;

    //变量
    ArrayList<String> mGroupNames;
    ArrayList<ArrayList<String>> mGroupItemUsers;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {
        mMessageExList = (ExpandableListView) view.findViewById(R.id.exlist_messagme);
        initMessageExList();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mMessageExList.setOnChildClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    private void initMessageExList() {
        mGroupNames = new ArrayList<>();
        mGroupItemUsers = new ArrayList<>();
        mMessageExAdapter = new MessageExAdapter(mContext, mGroupNames, mGroupItemUsers);
        mMessageExList.setAdapter(mMessageExAdapter);

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Intent intent=new Intent(mContext, ChatActivity.class);
        startActivity(intent);
        return true;
    }
}
