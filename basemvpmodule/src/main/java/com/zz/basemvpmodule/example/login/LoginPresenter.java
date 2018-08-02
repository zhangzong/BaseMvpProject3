package com.zz.basemvpmodule.example.login;


import com.zz.basemvpmodule.basemvp.BasePresenter;

/**
 * Created by zhangzong on 31/7/2018.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    public void login() {
        mView.loginSuccess();
    }
}