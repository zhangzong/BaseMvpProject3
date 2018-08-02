package com.zhangzong.basemvpmodule.basemvpproject.example.activity;


import com.zhangzong.basemvpmodule.basemvpproject.BaseMvpActivity;

/**
 * 例子4：不使用 mvp 的情况
 * Created by zhangzong on 31/7/2018.
 */

public class ExampleActivity4 extends BaseMvpActivity {
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {

    }
}