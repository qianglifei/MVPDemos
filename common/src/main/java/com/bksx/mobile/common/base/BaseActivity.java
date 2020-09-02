package com.bksx.mobile.common.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutId());
        initEvent();
    }

    /**
     * 初始化事件
     * @param
     */
    protected abstract void initEvent();

    /**
     *
     * @return 绑定布局id
     *
     */
    protected abstract int attachLayoutId();


}
