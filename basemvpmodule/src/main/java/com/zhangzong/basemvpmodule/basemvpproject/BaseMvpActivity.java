package com.zhangzong.basemvpmodule.basemvpproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhangzong.basemvpmodule.basemvpproject.basemvp.BaseMvpView;
import com.zhangzong.basemvpmodule.basemvpproject.basemvp.BasePresenter;
import com.zhangzong.basemvpmodule.basemvpproject.basemvp.PresenterDispatch;
import com.zhangzong.basemvpmodule.basemvpproject.basemvp.PresenterProviders;


/**
 * Created by zhangzong on 31/7/2018.
 */

public abstract class BaseMvpActivity<P  extends BasePresenter> extends AppCompatActivity implements BaseMvpView {

    private PresenterProviders mPresenterProviders;
    private PresenterDispatch mPresenterDispatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mPresenterProviders = PresenterProviders.inject(this);
        mPresenterDispatch = new PresenterDispatch(mPresenterProviders);

        mPresenterDispatch.attachView(this, this);
        mPresenterDispatch.onCreatePresenter(savedInstanceState);
        init();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenterDispatch.onSaveInstanceState(outState);
    }

    protected abstract int getContentView();

    public abstract void init();

    protected P getPresenter() {
        return mPresenterProviders.getPresenter(0);
    }

    public PresenterProviders getPresenterProviders() {
        return mPresenterProviders;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void complete() {

    }

    @Override
    public void showProgressUI(boolean isShow) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenterDispatch.detachView();
    }
}
