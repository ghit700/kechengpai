package com.ketangpai.fragment;

import android.view.View;
import android.widget.RelativeLayout;

import com.ketangpai.base.BaseFragment;
import com.ketangpai.nan.ketangpai.R;

/**
 * Created by nan on 2016/3/21.
 */
public class TAccountFragment extends BaseFragment implements View.OnClickListener {

    RelativeLayout mUserIcon, mName, mShool;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_t_acount;
    }

    @Override
    protected void initView() {
        mUserIcon = (RelativeLayout) view.findViewById(R.id.ll_account_userIcon);
        mShool = (RelativeLayout) view.findViewById(R.id.ll_account_school);
        mName = (RelativeLayout) view.findViewById(R.id.ll_account_name);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mName.setOnClickListener(this);
        mUserIcon.setOnClickListener(this);
        mShool.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_account_name:
                break;
            case R.id.ll_account_school:
                break;
            case R.id.ll_account_userIcon:
                break;

            default:
                break;
        }
    }
}
