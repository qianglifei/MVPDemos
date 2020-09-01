package com.bksx.mobile.common.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bksx.mobile.common.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutId());
    }

    /**
     *
     * @return 绑定布局id
     *
     */
    protected abstract int attachLayoutId();


}
