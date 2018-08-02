package com.zhangzong.basemvpmodule.basemvpproject.example.login;


import com.zhangzong.basemvpmodule.basemvpproject.basemvp.BasePresenter;

/**
 * Created by zhangzong on 31/7/2018.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    public void login() {
        mView.loginSuccess();
    }
}