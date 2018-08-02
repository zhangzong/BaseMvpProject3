package com.zhangzong.basemvpmodule.basemvpproject.example.activity;

import android.util.Log;

import com.zhangzong.basemvpmodule.basemvpproject.BaseMvpActivity;
import com.zhangzong.basemvpmodule.basemvpproject.basemvp.CreatePresenter;
import com.zhangzong.basemvpmodule.basemvpproject.example.login.LoginPresenter;
import com.zhangzong.basemvpmodule.basemvpproject.example.login.LoginView;
import com.zhangzong.basemvpmodule.basemvpproject.example.register.RegisterPresenter;
import com.zhangzong.basemvpmodule.basemvpproject.example.register.RegisterView;


/**
 * Created by zhangzong on 31/7/2018.
 * 例子2：多个Presenter和使用 getPresenter 方法获取实例
 */
@CreatePresenter(presenter = {LoginPresenter.class, RegisterPresenter.class})
public class ExampleActivity2 extends BaseMvpActivity implements LoginView, RegisterView {

    private LoginPresenter mLoginPresenter;
    private RegisterPresenter mRegisterPresenter;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        mLoginPresenter = getPresenterProviders().getPresenter(0);
        mRegisterPresenter = getPresenterProviders().getPresenter(1);

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
