package com.zz.basemvpmodule.example.activity;

import android.util.Log;

import com.zz.basemvpmodule.BaseMvpActivity;
import com.zz.basemvpmodule.R;
import com.zz.basemvpmodule.basemvp.CreatePresenter;
import com.zz.basemvpmodule.basemvp.PresenterVariable;
import com.zz.basemvpmodule.example.login.LoginPresenter;
import com.zz.basemvpmodule.example.login.LoginView;
import com.zz.basemvpmodule.example.register.RegisterPresenter;
import com.zz.basemvpmodule.example.register.RegisterView;

/**
 * Created by zhangzong on 31/7/2018.
 */

/**
 * 例子1：多个Presenter和使用@PresenterVariable注解
 */
@CreatePresenter(presenter = {LoginPresenter.class, RegisterPresenter.class})
public class ExampleActivity1 extends BaseMvpActivity implements LoginView, RegisterView {

    @PresenterVariable
    private LoginPresenter mLoginPresenter;
    @PresenterVariable
    private RegisterPresenter mRegisterPresenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        mLoginPresenter.login();
        mRegisterPresenter.register();
    }

    @Override
    public void loginSuccess() {
        Log.i("ExampleActivity1", "登陆成功");
    }

    @Override
    public void registerSuccess() {
        Log.i("ExampleActivity1", "注册成功");
    }
}
