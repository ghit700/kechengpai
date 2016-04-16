package com.ketangpai.presenter;

import android.util.Log;

import com.ketangpai.activity.LoginActivity;
import com.ketangpai.base.BasePresenter;
import com.ketangpai.bean.User;
import com.ketangpai.constant.Constant;
import com.ketangpai.model.UserModel;
import com.ketangpai.modelImpl.UserModelImpl;
import com.ketangpai.utils.VolleyUtils;
import com.ketangpai.viewInterface.LoginViewInterface;

/**
 * Created by nan on 2016/4/16.
 */
public class LoginPresenter extends BasePresenter<LoginViewInterface> {
    LoginViewInterface loginViewInterface;
    UserModel userModel;


    public LoginPresenter() {
        userModel = new UserModelImpl();
    }

    public void login(String account, String password, int type) {
        if (isViewAttached()) {
            loginViewInterface = getView();
        } else {
            Log.i(LoginActivity.TAG,"没有连接view");
            return;
        }

        loginViewInterface.showLoginLoading();

        userModel.login(account, password, type, new VolleyUtils.ResultCallback() {
            @Override
            public void onSuccess(String result) {
                loginViewInterface.login(Integer.valueOf(result));
                loginViewInterface.hideLoginLoading();
            }

            @Override
            public void onError(String error) {
                Log.i(Constant.LOG_TAG, error);
            }
        });
    }

    public void register(User user, int type) {
        if (isViewAttached()) {
            loginViewInterface = getView();
        } else {
            Log.i(LoginActivity.TAG,"没有连接view");
            return;
        }

        loginViewInterface.showRegisterLoading();

        userModel.register(user, type, new VolleyUtils.ResultCallback() {
            @Override
            public void onSuccess(String result) {
                loginViewInterface.register(Integer.valueOf(result));
                loginViewInterface.hideRegisterLoading();
            }

            @Override
            public void onError(String error) {

            }
        });
    }
}
