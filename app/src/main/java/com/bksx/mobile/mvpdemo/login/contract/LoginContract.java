package com.bksx.mobile.mvpdemo.login.contract;

import android.content.Context;

import com.bksx.mobile.common.base.IBaseModel;
import com.bksx.mobile.common.base.IBasePresenter;
import com.bksx.mobile.common.base.IBaseView;

import io.reactivex.Flowable;


/**
 * @author :qlf
 */
public interface LoginContract {

   interface Model extends IBaseModel{
       Flowable<String> login(String requestBody, Presenter iPresenter);
   }

   interface Presenter extends IBasePresenter{
       void postSendLoginData(Context context, String sj);
   }

   interface View extends IBaseView<Presenter>{
       void updateUI(String result);
   }
}
