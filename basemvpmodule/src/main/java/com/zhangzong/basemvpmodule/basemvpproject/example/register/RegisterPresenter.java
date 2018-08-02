package com.zhangzong.basemvpmodule.basemvpproject.example.register;


import com.zhangzong.basemvpmodule.basemvpproject.basemvp.BasePresenter;

/**
 * Created by zhangzong on 31/7/2018.
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {

    public void register() {
        mView.registerSuccess();
    }
}
