package com.ketangpai.model;

import com.ketangpai.bean.User;
import com.ketangpai.utils.VolleyUtils;

/**
 * Created by nan on 2016/4/16.
 */
public interface UserModel {
    public void login(String account, String password, int type, VolleyUtils.ResultCallback resultCallback);

    public void register(User user, int type, VolleyUtils.ResultCallback resultCallback);
}