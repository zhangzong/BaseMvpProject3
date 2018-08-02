package com.zz.basemvpmodule.example.activity;

import android.util.Log;

import com.zz.basemvpmodule.BaseMvpFragment;
import com.zz.basemvpmodule.basemvp.CreatePresenter;
import com.zz.basemvpmodule.basemvp.PresenterVariable;
import com.zz.basemvpmodule.example.login.LoginPresenter;
import com.zz.basemvpmodule.example.login.LoginView;
import com.zz.basemvpmodule.example.register.RegisterPresenter;
import com.zz.basemvpmodule.example.register.RegisterView;


/**
 * Created by zhangzong on 31/7/2018.
 */

@CreatePresenter(presenter = {LoginPresenter.class, RegisterPresenter.class})
public class ExampleFragment extends BaseMvpFragment implements LoginView, RegisterView {

    @PresenterVariable
    private LoginPresenter mLoginPresenter;
    @PresenterVariable
    private RegisterPresenter mRegisterPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mLoginPresenter.login();
        mRegisterPresenter.register();
    }

    @Override
    public void loginSuccess() {
        Log.i("ExampleFragment", "登陆成功");
    }

    @Override
    public void registerSuccess() {
        Log.i("ExampleFragment", "注册成功");
    }
}