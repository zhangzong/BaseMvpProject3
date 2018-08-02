package com.zhangzong.basemvpmodule.basemvpproject.example.activity;

import android.util.Log;

import com.zhangzong.basemvpmodule.basemvpproject.BaseMvpFragment;
import com.zhangzong.basemvpmodule.basemvpproject.basemvp.CreatePresenter;
import com.zhangzong.basemvpmodule.basemvpproject.basemvp.PresenterVariable;
import com.zhangzong.basemvpmodule.basemvpproject.example.login.LoginPresenter;
import com.zhangzong.basemvpmodule.basemvpproject.example.login.LoginView;
import com.zhangzong.basemvpmodule.basemvpproject.example.register.RegisterPresenter;
import com.zhangzong.basemvpmodule.basemvpproject.example.register.RegisterView;


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