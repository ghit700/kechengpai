package com.ketangpai.activity;

import android.support.v4.app.Fragment;

import com.ketangpai.base.DrawerBaseActivity;
import com.ketangpai.nan.ketangpai.R;

/**
 * Created by nan on 2016/4/10.
 */
public class THomeWorkActivity extends DrawerBaseActivity {


    //variables
    private String homeworkName;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_nevigation;
    }

    @Override
    protected Fragment getLayoutFragment() {
        return null;
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        if (null != getIntent().getStringExtra("HomeWorkName")) {
            homeworkName = getIntent().getStringExtra("HomeWorkName");
        }
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        if (null != homeworkName) {

            getSupportActionBar().setTitle(homeworkName);
        }
    }
}
