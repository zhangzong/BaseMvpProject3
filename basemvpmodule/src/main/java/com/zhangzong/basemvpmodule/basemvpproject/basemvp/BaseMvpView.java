package com.zhangzong.basemvpmodule.basemvpproject.basemvp;

/**
 * Created by zhangzong on 31/7/2018.
 */

public interface BaseMvpView {
    void showError(String msg);

    void complete();

    void showProgressUI(boolean isShow);
}
