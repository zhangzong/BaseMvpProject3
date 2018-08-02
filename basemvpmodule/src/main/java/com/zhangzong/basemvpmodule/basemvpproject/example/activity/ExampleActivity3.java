package com.zhangzong.basemvpmodule.basemvpproject.example.activity;

import android.util.Log;

import com.zhangzong.basemvpmodule.basemvpproject.BaseMvpActivity;
import com.zhangzong.basemvpmodule.basemvpproject.basemvp.CreatePresenter;
import com.zhangzong.basemvpmodule.basemvpproject.example.login.LoginPresenter;
import com.zhangzong.basemvpmodule.basemvpproject.example.login.LoginView;


/**
 * Created by zhangzong on 31/7/2018.
 * 例子3：一个Presenter和使用 getPresenter 方法获取实例
 */
@CreatePresenter(presenter = LoginPresenter.class)
public class ExampleActivity3 extends BaseMvpActivity<LoginPresenter> implements LoginView {

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        getPresenter().login();
    }

    @Override
    public void loginSuccess() {
        Log.i("ExampleActivity1", "登陆成功");
    }
}