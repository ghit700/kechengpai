package com.ketangpai.activity;

import android.support.v4.app.Fragment;
import android.view.View;

import com.ketangpai.base.DrawerBaseActivity;
import com.ketangpai.fragment.NotificationFragment;
import com.ketangpai.nan.ketangpai.R;

/**
 * Created by nan on 2016/3/19.
 */
public class NotificationActivity extends DrawerBaseActivity {


    @Override
    protected int getContentViewId() {
        return R.layout.activity_base_nevigation;
    }

    @Override
    protected Fragment getLayoutFragment() {
        return new NotificationFragment();
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
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void onItemClick(View view, int position) {
        super.onItemClick(view, position);
    }

    @Override
    protected void loadData() {

    }
}
