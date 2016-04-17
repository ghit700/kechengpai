package com.ketangpai.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ketangpai.adapter.MessageAdapter;
import com.ketangpai.base.BaseFragment;
import com.ketangpai.nan.ketangpai.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nan on 2016/4/17.
 */
public class MessageFragment extends BaseFragment {

    //view
    RecyclerView list_messages;

    //adater
    MessageAdapter mMessageAdapter;

    //变量
    private String account;
    List mMessages;

    @Override
    protected void initVarious() {
        super.initVarious();
        account = mContext.getSharedPreferences("user", 0).getString("account", "");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {
        list_messages = (RecyclerView) view.findViewById(R.id.list_messagme);

        list_messages.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mMessages = new ArrayList();
        mMessageAdapter = new MessageAdapter(mContext, mMessages);
        list_messages.setAdapter(mMessageAdapter);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; ++i) {
            mMessageAdapter.addItem(i, "111");
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }
}
