package com.bksx.mobile.mvpdemo.login.ui;

import android.content.Context;
import android.util.Log;

import com.bksx.mobile.common.base.OrdinaryBaseActivity;
import com.bksx.mobile.common.util.MD5Util;
import com.bksx.mobile.mvpdemo.R;
import com.bksx.mobile.mvpdemo.login.contract.LoginContract;
import com.bksx.mobile.mvpdemo.login.moudle.LoginBean;
import com.bksx.mobile.mvpdemo.login.moudle.LoginBeanTest;
import com.bksx.mobile.mvpdemo.login.presenter.LoginPresenterImpl;

import org.json.JSONObject;

import java.util.HashMap;

import static com.bksx.mobile.mvpdemo.login.contract.LoginContract.*;

public class LoginActivity extends OrdinaryBaseActivity<LoginContract.Presenter> implements LoginContract.View{

    @Override
    protected void initEvent() {
        LoginBean bean = new LoginBean();
        bean.setUsername("zggj");
        bean.setPassword(MD5Util.md5("0"));
//        LoginBeanTest bean = new LoginBeanTest();
//        bean.setYhzh("15210603710");
//        bean.setYhmm("a00000");
        mPresenter.postSendLoginData(mContext,bean);
    }

    @Override
    protected int attachLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void updateUI(String result) {
        Log.i("TAG", "===updateUI: " + result);
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void setPresenter(Presenter presenter) {
        if (presenter == null){
            mPresenter = new LoginPresenterImpl(this);
        }else {
            Log.i("TAG", "setPresenter: + Presenter为空");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 解除绑定
         */
        mPresenter = null;
    }
}
