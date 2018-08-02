package com.zhangzong.basemvpmodule.basemvpproject.example.activity;


import com.zhangzong.basemvpmodule.basemvpproject.BaseMvpActivity;

/**
 * Created by zhangzong on 31/7/2018.
 * 例子4：Fragment
 */

public class ExampleActivity5 extends BaseMvpActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_fragment;
    }

    @Override
    public void init() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_layout, new ExampleFragment())
                .commit();
    }
}
