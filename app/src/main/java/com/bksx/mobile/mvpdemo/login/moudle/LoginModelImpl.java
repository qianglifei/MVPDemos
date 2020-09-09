package com.bksx.mobile.mvpdemo.login.moudle;

import com.bksx.mobile.common.httputils.OkHttpUtils;
import com.bksx.mobile.mvpdemo.application.BaseApplication;
import com.bksx.mobile.mvpdemo.config.URLConfig;
import com.bksx.mobile.mvpdemo.homepage.INewsArticle;
import com.bksx.mobile.mvpdemo.login.contract.LoginContract;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * @author :qlf
 *
 */
public class LoginModelImpl implements LoginContract.Model {

    @Override
    public Observable<String> login(Object requestBody, LoginContract.Presenter iPresenter) {
        return OkHttpUtils.
                getInstance(BaseApplication.getContext()).
                postJsonRequest(BaseApplication.getContext(), URLConfig.BASE_URL + "user/log", requestBody).
                subscribeOn(Schedulers.io());
    }
}
