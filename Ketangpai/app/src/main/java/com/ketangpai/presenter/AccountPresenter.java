package com.ketangpai.presenter;

import android.util.Log;

import com.ketangpai.base.BasePresenter;
import com.ketangpai.fragment.AccountFragment;
import com.ketangpai.model.UserModel;
import com.ketangpai.modelImpl.UserModelImpl;
import com.ketangpai.utils.VolleyUtils;
import com.ketangpai.viewInterface.AccountViewInterface;

/**
 * Created by Administrator on 2016/4/18.
 */
public class AccountPresenter extends BasePresenter<AccountViewInterface> {
    UserModel userModel;
    AccountViewInterface accountViewInterface;

    public AccountPresenter() {
        userModel = new UserModelImpl();
    }

    public void updateUserInfo(String account, String columnName, String columnValue) {
        if (isViewAttached()) {
            accountViewInterface = getView();
        } else {
            Log.i(AccountFragment.TAG, "没有连接viwe");
        }

        userModel.updateUserInfo(account, columnName, columnValue, new VolleyUtils.ResultCallback() {
            @Override
            public void onSuccess(String result) {
                accountViewInterface.updateUserInfoOnComplete(result);
            }

            @Override
            public void onError(String error) {
                Log.i(AccountFragment.TAG, error);
            }
        });
    }

}
