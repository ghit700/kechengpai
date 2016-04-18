package com.ketangpai.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.ketangpai.base.BaseFragment;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.utils.ImageLoaderUtils;
import com.ketangpai.utils.IntentUtils;

/**
 * Created by nan on 2016/3/21.
 */
public class AccountFragment extends BaseFragment implements View.OnClickListener {

    RelativeLayout mUserIcon, mName, mShool, mNumber, mPassword;
    TextView tv_account, tv_school, tv_name, tv_number;
    ImageView img_account_user;
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
        img_account_user = (ImageView) view.findViewById(R.id.img_account_user);

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
            tv_number.setText(String.valueOf(number));
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
                IntentUtils.openImageFile(this);
                break;
            case R.id.ll_account_sid:
                break;
            case R.id.ll_account_password:
                showUpdatePasswordDialog();
                break;

            default:
                break;
        }
    }

    private void showUpdatePasswordDialog() {
        AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_account_update_password, null);

        dialog.setContentView(view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentUtils.OPEN_IMGAE && resultCode == getActivity().RESULT_OK) {
            ImageLoaderUtils.display(mContext, img_account_user, data.getData().getPath());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
