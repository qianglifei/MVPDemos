package com.bksx.mobile.mvpdemo.login.moudle;

import com.bksx.mobile.common.httputils.OkHttpUtils;
import com.bksx.mobile.mvpdemo.homepage.INewsArticle;
import com.bksx.mobile.mvpdemo.login.contract.LoginContract;

import java.util.Observable;

import io.reactivex.Flowable;

/**
 *
 * @author :qlf
 *
 */
public class LoginModelImpl implements LoginContract.Model {

    @Override
    public Flowable<String> login(String requestBody, LoginContract.Presenter iPresenter) {

        return OkHttpUtils.getInstance().postJsonSecurity();
    }
}
