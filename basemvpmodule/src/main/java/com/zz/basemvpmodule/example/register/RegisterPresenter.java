package com.zz.basemvpmodule.example.register;


import com.zz.basemvpmodule.basemvp.BasePresenter;

/**
 * Created by zhangzong on 31/7/2018.
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {

    public void register() {
        mView.registerSuccess();
    }
}
