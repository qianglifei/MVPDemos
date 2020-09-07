package com.bksx.mobile.mvpdemo.login.presenter;

import android.content.Context;
import android.util.Log;

import com.bksx.mobile.mvpdemo.application.BaseApplication;
import com.bksx.mobile.mvpdemo.login.contract.LoginContract;
import com.bksx.mobile.mvpdemo.login.moudle.LoginModelImpl;

import org.json.JSONObject;
import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

import static android.content.ContentValues.TAG;

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
    public void postSendLoginData(Context context, Object qq) {
        Observable<String> login = iLoginModel.login(qq, this);
        //订阅观察者
        login.as(iLoginView.bindAutoDispose()).
         subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: .");
                iLoginView.updateUI(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
