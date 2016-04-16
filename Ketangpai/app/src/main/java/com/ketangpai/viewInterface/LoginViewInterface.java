package com.ketangpai.viewInterface;

/**
 * Created by nan on 2016/4/16.
 */
public interface LoginViewInterface {
    /**
     * 登录
     *
     * @param ret ret>=1 登录成功,否则登录失败
     */
    public void login(int ret);

    public void showLoginLoading();

    public void hideLoginLoading();

    public void showRegisterLoading();

    public void hideRegisterLoading();

    /**
     * @param ret ret>=1 注册成功 =0 已存在账号 -1注册失败
     */
    public void register(int ret);
}
