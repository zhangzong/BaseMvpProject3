package com.zz.basemvpmodule.example.activity;

import com.zz.basemvpmodule.R;
import com.zz.basemvpmodule.BaseMvpActivity;

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
