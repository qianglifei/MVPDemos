package com.bksx.mobile.mvpdemo.login.contract;

import android.content.Context;

import com.bksx.mobile.common.base.IBaseModel;
import com.bksx.mobile.common.base.IBasePresenter;
import com.bksx.mobile.common.base.IBaseView;

import io.reactivex.Flowable;
import io.reactivex.Observable;


/**
 * @author :qlf
 */
public interface LoginContract {

   interface Model extends IBaseModel{
       Observable<String> login(Object requestBody, Presenter iPresenter);
   }

   interface Presenter extends IBasePresenter{
       void postSendLoginData(Context context, Object sj);
   }

   interface View extends IBaseView<Presenter>{
       void updateUI(String result);
   }
}
