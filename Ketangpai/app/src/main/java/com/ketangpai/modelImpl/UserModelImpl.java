package com.ketangpai.modelImpl;

import com.ketangpai.activity.LoginActivity;
import com.ketangpai.bean.Student;
import com.ketangpai.bean.User;
import com.ketangpai.constant.Constant;
import com.ketangpai.constant.Urls;
import com.ketangpai.model.UserModel;
import com.ketangpai.utils.VolleyUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nan on 2016/4/16.
 */
public class UserModelImpl implements UserModel {


    @Override
    public void login(String account, String password, int type, VolleyUtils.ResultCallback resultCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("account", account);
        params.put("password", password);
        params.put("type", String.valueOf(type));
        String url = Urls.SERVER_HOST + Urls.LOGIN;
        VolleyUtils.post(url, LoginActivity.TAG, params, resultCallback);
    }

    @Override
    public void register(User user, int type, VolleyUtils.ResultCallback resultCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("account", user.getAccount());
        params.put("type", String.valueOf(user.getType()));
        params.put("password", user.getPassword());
        params.put("school", user.getSchool());
        if (type == 1) {
            params.put("number", String.valueOf(((Student) user).getNumber()));
        }
        String url = Urls.SERVER_HOST + Urls.REGISTER;
        VolleyUtils.post(url, LoginActivity.TAG, params, resultCallback);
    }
}
