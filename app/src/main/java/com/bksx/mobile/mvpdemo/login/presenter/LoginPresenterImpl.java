package com.bksx.mobile.mvpdemo.login.presenter;

import android.content.Context;

import com.bksx.mobile.mvpdemo.login.contract.LoginContract;
import com.bksx.mobile.mvpdemo.login.moudle.LoginModelImpl;

/**
 * @author :qlf
 */
public class LoginPresenterImpl implements LoginContract.Presenter {

    private LoginContract.Model iLoginModel;
    private LoginContract.View iLoginView;

    public LoginPresenterImpl(LoginContract.View iLoginView){
        this.iLoginView = iLoginView;
        iLoginModel = new LoginModelImpl();
    }

    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowNetError() {

    }

    @Override
    public void postSendLoginData(Context context, String qq) {

    }
}
