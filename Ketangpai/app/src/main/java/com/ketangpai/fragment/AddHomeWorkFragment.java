package com.ketangpai.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ketangpai.adapter.AddHomeWorkDataAdapter;
import com.ketangpai.base.BaseFragment;
import com.ketangpai.nan.ketangpai.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by nan on 2016/3/27.
 */
public class AddHomeWorkFragment extends BaseFragment {

    //view
    EditText etHomeworkTitle;
    EditText etAddHomeworkContent;
    TextView tvAddHomeworkEndDate;
    TextView tvAddHomeworkEndTime;
    RecyclerView listAddHomeworkData;

    //adapter
    AddHomeWorkDataAdapter mAddHomeWorkDataAdapter;

    //变量
    List mDataList;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homework;
    }

    @Override
    protected void initView() {
        etHomeworkTitle = (EditText) view.findViewById(R.id.et_homework_title);
        etAddHomeworkContent = (EditText) view.findViewById(R.id.et_add_homework_content);
        tvAddHomeworkEndDate = (TextView) view.findViewById(R.id.tv_add_homework_endDate);
        tvAddHomeworkEndTime = (TextView) view.findViewById(R.id.tv_add_homework_endTime);
        initDataList();
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; ++i) {
            mAddHomeWorkDataAdapter.addItem(i,"111");
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

    }


    private void initDataList() {
        listAddHomeworkData= (RecyclerView) view.findViewById(R.id.list_add_homework_data);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        listAddHomeworkData.setLayoutManager(linearLayoutManager);
        mDataList=new ArrayList();
        mAddHomeWorkDataAdapter=new AddHomeWorkDataAdapter(mContext,mDataList);
        listAddHomeworkData.setAdapter(mAddHomeWorkDataAdapter);



    }
}
