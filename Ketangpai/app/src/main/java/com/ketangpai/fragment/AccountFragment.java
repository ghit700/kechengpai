package com.ketangpai.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.ketangpai.base.BaseFragment;
import com.ketangpai.base.BasePresenter;
import com.ketangpai.base.BasePresenterFragment;
import com.ketangpai.nan.ketangpai.R;
import com.ketangpai.presenter.AccountPresenter;
import com.ketangpai.utils.ImageLoaderUtils;
import com.ketangpai.utils.IntentUtils;
import com.ketangpai.viewInterface.AccountViewInterface;

/**
 * Created by nan on 2016/3/21.
 */
public class AccountFragment extends BasePresenterFragment<AccountViewInterface, AccountPresenter> implements View.OnClickListener, AccountViewInterface {

    public static final String TAG = "===accountfragment";

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
    private InputMethodManager mImm;

    @Override
    protected void initVarious() {
        super.initVarious();
        school = mContext.getSharedPreferences("user", 0).getString("school", "");
        name = mContext.getSharedPreferences("user", 0).getString("name", "");
        account = mContext.getSharedPreferences("user", 0).getString("account", "");
        password = mContext.getSharedPreferences("user", 0).getString("password", "");
        type = mContext.getSharedPreferences("user", 0).getInt("type", -1);
        number = mContext.getSharedPreferences("user", 0).getInt("number", -1);
        mImm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
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
    protected AccountPresenter createPresenter() {
        return new AccountPresenter();
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
        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_account_update_password, null);

        mImm.showSoftInput(view,InputMethodManager.SHOW_FORCED);

        final EditText et_dialog_password_confirm = (EditText) view.findViewById(R.id.et_dialog_password_confirm);
        final EditText et_dialog_password_new = (EditText) view.findViewById(R.id.et_dialog_password_new);
        final EditText et_dialog_password_pass = (EditText) view.findViewById(R.id.et_dialog_password_pass);
        Button btn_dialog_password_save = (Button) view.findViewById(R.id.btn_dialog_password_save);
        Button btn_dialog_password_cancel = (Button) view.findViewById(R.id.btn_dialog_password_cancel);

        btn_dialog_password_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_dialog_password_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String confirmPwd = et_dialog_password_confirm.getText().toString();
                String newPwd = et_dialog_password_new.getText().toString();
                String passPwd = et_dialog_password_pass.getText().toString();
                updatePassword(passPwd, newPwd, confirmPwd, dialog);
            }

        });


        dialog.setContentView(view);
    }

    private void updatePassword(String passPwd, String newPwd, String confirmPwd, AlertDialog dialog) {
        if (!passPwd.equals("") && !newPwd.equals("") && !confirmPwd.equals("")) {
            if (passPwd.equals(password)) {
                if (!newPwd.equals(confirmPwd)) {
                    showUpdateDialog("密码", 2);
                } else {

                    mPresenter.updateUserInfo(account, "password", newPwd);
                    mContext.getSharedPreferences("user", 0).edit().putString("password", newPwd).commit();
                    dialog.dismiss();
                    showUpdateDialog("密码", 3);
                }
            } else {
                showUpdateDialog("密码", 1);
            }
        } else {
            showUpdateDialog("密码", 0);
        }
    }

    /**
     * 显示修改失败对话框
     *
     * @param colunmnName 修改哪一项
     * @param type        失败的原因
     */
    private void showUpdateDialog(String colunmnName, int type) {
        StringBuffer title = new StringBuffer();
        StringBuffer message = new StringBuffer();
        title = title.append(colunmnName);
        Log.i("wu", type + "");
        switch (type) {
            case 0:
                message = message.append("不能为空,请重新填写");
                title.append("修改失败");
                break;
            case 1:
                title.append("修改失败");
                message = message.append("旧密码输入错误,请重新填写");
                break;
            case 2:
                title.append("修改失败");
                message = message.append("两次输入的密码不一致,请重新填写");
                break;
            case 3:
                title.append("修改成功");
                break;
            default:
                break;
        }
        if (type == 3) {
            new AlertDialog.Builder(mContext).setTitle(title.toString())
                    .setPositiveButton("确认", null).create().show();
        } else {
            new AlertDialog.Builder(mContext).setTitle(title.toString()).setMessage(message.toString())
                    .setPositiveButton("确认", null).create().show();

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentUtils.OPEN_IMGAE && resultCode == getActivity().RESULT_OK) {

            ImageLoaderUtils.display(mContext, img_account_user, data.getData().getPath());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void updateUserInfoOnComplete(String columnName) {
        if (columnName.equals("-1")) {
            new AlertDialog.Builder(mContext).setTitle("修改失败").setPositiveButton("确认", null).create().show();
        } else {
            Log.i(TAG, columnName);

            switch (columnName) {
                case "password":
                    break;
                case "school":
                    break;
                case "number":
                    break;
                case "name":
                    break;
                default:
                    break;
            }
        }
    }
}
