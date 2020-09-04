package com.bksx.mobile.mvpdemo.login.ui;

import android.content.Context;

import com.bksx.mobile.common.base.OrdinaryBaseActivity;
import com.bksx.mobile.mvpdemo.R;
import com.bksx.mobile.mvpdemo.login.contract.LoginContract;
import com.bksx.mobile.mvpdemo.login.presenter.LoginPresenterImpl;

import org.json.JSONObject;

import java.util.HashMap;

import static com.bksx.mobile.mvpdemo.login.contract.LoginContract.*;

public class LoginActivity extends OrdinaryBaseActivity<LoginContract.Presenter> implements LoginContract.View{

    @Override
    protected void initEvent() {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("yhzh","15210603710");
        hashMap.put("yhmm","a00000");
        String obj = new JSONObject(hashMap).toString();

        mPresenter.postSendLoginData(mContext,obj);
    }

    @Override
    protected int attachLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void updateUI(String result) {

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
        if (presenter != null){
            mPresenter = new LoginPresenterImpl(this);
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
