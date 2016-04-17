package com.ketangpai.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ketangpai.base.BaseFragment;
import com.ketangpai.nan.ketangpai.R;

/**
 * Created by nan on 2016/3/21.
 */
public class AccountFragment extends BaseFragment implements View.OnClickListener {

    RelativeLayout mUserIcon, mName, mShool, mNumber, mPassword;
    TextView tv_account, tv_school, tv_name, tv_number;
    //变量
    private int type;
    private String school;
    private int number;
    private String name;
    private String account;
    private String password;


    @Override
    protected void initVarious() {
        super.initVarious();
        school = mContext.getSharedPreferences("user", 0).getString("school", "");
        name = mContext.getSharedPreferences("user", 0).getString("name", "");
        account = mContext.getSharedPreferences("user", 0).getString("account", "");
        password = mContext.getSharedPreferences("user", 0).getString("password", "");
        type = mContext.getSharedPreferences("user", 0).getInt("type", -1);
        number = mContext.getSharedPreferences("user", 0).getInt("number", -1);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    protected void initView() {
        mUserIcon = (RelativeLayout) view.findViewById(R.id.ll_account_userIcon);
        mShool = (RelativeLayout) view.findViewById(R.id.ll_account_school);
        mName = (RelativeLayout) view.findViewById(R.id.ll_account_name);
        mNumber = (RelativeLayout) view.findViewById(R.id.ll_account_sid);
        mPassword = (RelativeLayout) view.findViewById(R.id.ll_account_password);
        tv_account = (TextView) view.findViewById(R.id.tv_account);
        tv_school = (TextView) view.findViewById(R.id.tv_school);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_number = (TextView) view.findViewById(R.id.tv_number);

        if (type == 0) {
            mNumber.setVisibility(view.GONE);
        }
    }

    @Override
    protected void initData() {
        tv_account.setText(account);
        tv_school.setText(school);
        tv_name.setText(name);

        if (type == 1) {
            tv_account.setText(number);
        }
    }

    @Override
    protected void initListener() {
        mName.setOnClickListener(this);
        mUserIcon.setOnClickListener(this);
        mShool.setOnClickListener(this);
        mNumber.setOnClickListener(this);
        mPassword.setOnClickListener(this);
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
            case R.id.ll_account_sid:
                break;
            case R.id.ll_account_password:
                break;

            default:
                break;
        }
    }
}
