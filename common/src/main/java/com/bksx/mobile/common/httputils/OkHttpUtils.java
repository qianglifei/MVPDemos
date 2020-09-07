package com.bksx.mobile.common.httputils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.reactivestreams.Subscriber;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import bksx.com.soymilk.app.ApplicationInformation;
import bksx.com.soymilk.bean.ActionDataBean;
import bksx.com.soymilk.location.LocationService;
import bksx.com.soymilk.system.SystemInformation;
import bksx.com.soymilk.util.ConfigUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author :qlf
 */
public class OkHttpUtils {
    private static  OkHttpUtils httpClient;
    private OkHttpClient client;
    private Handler mHandler;
    private String BEIKONGYUN_ACTION_URL = "http://cloud.beikongyun.com/services/logserver/api/logserver-kafka/log";
    private String BEIKONGYUN_API_KEY = "";
    private String BEIKONGYUN_PACKAGE_NAME = "";
    private String BEIKONGYUN_SHA1 = "";

    public OkHttpUtils() {

    }

    protected OkHttpUtils(Context context) {
        File sdcache = context.getExternalCacheDir();
        int cacheSize = 10485760;
        OkHttpClient.Builder builder = (new OkHttpClient.Builder()).connectTimeout(15L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).cache(new Cache(sdcache.getAbsoluteFile(), (long)cacheSize));
        this.client = builder.build();
        this.mHandler = new Handler();
    }


    public static OkHttpUtils getInstance(Context context) {
        if (httpClient == null) {
            Class var1 = OkHttpUtils.class;
            synchronized(OkHttpUtils.class) {
                if (httpClient == null) {
                    httpClient = new OkHttpUtils(context);
                }
            }
        }

        return httpClient;
    }

    public void getAsyn(Context context, String url, final OkHttpUtils.ResultCallback callback) {
        Request request = (new okhttp3.Request.Builder()).url(url).build();
        this.client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                OkHttpUtils.this.sendFailedCallback(call, e, callback);
            }

            public void onResponse(Call call, Response response) throws IOException {
                OkHttpUtils.this.sendSuccessCallback(response, callback);
            }
        });
    }

    public void postJsonAsyn(Context context, String url, Object object, final OkHttpUtils.ResultCallback callback) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = (new okhttp3.Request.Builder()).url(url).post(requestBody).build();
        this.client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                OkHttpUtils.this.sendFailedCallback(call, e, callback);
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response != null) {
                    OkHttpUtils.this.sendSuccessCallback(response, callback);
                }

            }
        });
    }

    public void postJsonAsyn(Context context, String url, Object object, String header_key, String header_value, final OkHttpUtils.ResultCallback callback) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = (new okhttp3.Request.Builder()).url(url).addHeader(header_key, header_value).post(requestBody).build();
        this.client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                OkHttpUtils.this.sendFailedCallback(call, e, callback);
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response != null) {
                    OkHttpUtils.this.sendSuccessCallback(response, callback);
                }

            }
        });
    }

    private void sendFailedCallback(final Call call, final IOException e, final OkHttpUtils.ResultCallback callback) {
        this.mHandler.post(new Runnable() {
            public void run() {
                Log.i("httpclient", "bksx-soymilk：http请求失败；发起请求线程：" + Thread.currentThread().getName());
                if (callback != null) {
                    callback.onError(call.request(), e);
                }

            }
        });
    }

    private void sendSuccessCallback(final Response response, final OkHttpUtils.ResultCallback callback) {
        this.mHandler.post(new Runnable() {
            public void run() {
                Log.i("httpclient", "bksx-soymilk：http请求成功；发起请求线程：" + Thread.currentThread().getName());
                if (callback != null) {
                    try {
                        callback.onResponse(response);
                    } catch (IOException var2) {
                        var2.printStackTrace();
                    }
                }

            }
        });
    }

    public void postJsonSecurity(Context context, String post_url, Object object, OkHttpUtils.ResultCallback callback) {
        this.postJsonAsyn(context, post_url, object, callback);
        String api_key = ConfigUtil.getMetaDataFromAppication(context, "BEIKONGYUN_API_KEY");
        String package_name = ApplicationInformation.getPackageName(context);
        String api_sha1 = ApplicationInformation.getSingInfo(context.getApplicationContext(), package_name, "SHA1");
        ActionDataBean actionDataBean = this.getActionData(context, post_url);
        Gson gson = new Gson();
        String json = gson.toJson(actionDataBean);
        System.out.println(json);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String secertData = api_key + api_sha1 + package_name + formatter.format(curDate);
        String s = new String(Hex.encodeHex(DigestUtils.sha1(secertData)));
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = (new okhttp3.Request.Builder()).
                addHeader("key", api_key).
                addHeader("signature", s).
                addHeader("timestamp", formatter.format(curDate)).
                url(this.BEIKONGYUN_ACTION_URL).
                post(requestBody).
                build();
        System.out.println(request.body().toString());
        System.out.println(request.headers().toString());
        this.client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response != null) {
                    String resultType = response.body().string();
                    Log.i("AK认证", resultType);
                    if (resultType.equalsIgnoreCase("true")) {

                    }
                }

            }
        });
    }

    public Observable<String> postJsonSecuritys(Context context, String post_url, Object object) {
       // this.postJsonAsyn(context, post_url, object, callback);
        String api_key = ConfigUtil.getMetaDataFromAppication(context, "BEIKONGYUN_API_KEY");
        String package_name = ApplicationInformation.getPackageName(context);
        String api_sha1 = ApplicationInformation.getSingInfo(context.getApplicationContext(), package_name, "SHA1");
        ActionDataBean actionDataBean = this.getActionData(context, post_url);
        Gson gson = new Gson();
        String json = gson.toJson(actionDataBean);
        System.out.println(json);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String secertData = api_key + api_sha1 + package_name + formatter.format(curDate);
        String s = new String(Hex.encodeHex(DigestUtils.sha1(secertData)));
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        final Request request = (new okhttp3.Request.Builder()).
                addHeader("key", api_key).
                addHeader("signature", s).
                addHeader("timestamp", formatter.format(curDate)).
                url(this.BEIKONGYUN_ACTION_URL).
                post(requestBody).
                build();
        assert request.body() != null;
        System.out.println(request.body().toString());
        System.out.println(request.headers().toString());
        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        emitter.onError(new Exception("error"));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        emitter.onNext(string);          	// ** 1 **
                        emitter.onComplete();
                    }
                });
            }
//            @Override
//            public void call(final Subscriber<? super String> subscriber) {
//                mOkhttpClient = new OkHttpClient();
//                RequestBody formBody = new FormBody.Builder()
//                        .add("accountId", str)
//                        .build();
//                Request request = new Request.Builder()
//                        .url("http://api.yesapi.cn/?s=App.Hello.World")
//                        .post(formBody)
//                        .build();
//                Call call = mOkhttpClient.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        subscriber.onError(new Exception("error"));
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        String string = response.body().string();
//                        subscriber.onNext(string);          	// ** 1 **
//                        subscriber.onCompleted();
//
//                    }
//                });
//
//            }
        });
        return observable;
    }

    public ActionDataBean getActionData(Context context, String url) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());
        ActionDataBean actionDataBean = new ActionDataBean();
        actionDataBean.setDevice_brand(SystemInformation.getDeviceBrand());
        actionDataBean.setDevice_model(SystemInformation.getSystemModel());
        actionDataBean.setDevice_version(SystemInformation.getSystemVersion());
        actionDataBean.setDevice_imei(SystemInformation.getIMEI(context));
        actionDataBean.setDevice_time(formatter.format(curDate));
        actionDataBean.setDevice_latitude(LocationService.latitude + "");
        actionDataBean.setDevice_longitude(LocationService.longitude + "");
        actionDataBean.setApp_name(ApplicationInformation.getAppName(context));
        actionDataBean.setApp_version_code(ApplicationInformation.getVersionCode(context) + "");
        actionDataBean.setApp_version_name(ApplicationInformation.getVersionName(context));
        actionDataBean.setApp_package_name(ApplicationInformation.getPackageName(context));
        actionDataBean.setApp_class_name(context.getClass().getName().substring(context.getClass().getName().lastIndexOf(".") + 1));
        actionDataBean.setApp_post_url(url);
        return actionDataBean;
    }

    public void postActionData(Context context, String post_url, final OkHttpUtils.ResultCallback callback) {
        String api_key = ConfigUtil.getMetaDataFromAppication(context, "BEIKONGYUN_API_KEY");
        String package_name = ApplicationInformation.getPackageName(context);
        String api_sha1 = ApplicationInformation.getSingInfo(context.getApplicationContext(), package_name, "SHA1");
        ActionDataBean actionDataBean = this.getActionData(context, post_url);
        Gson gson = new Gson();
        String json = gson.toJson(actionDataBean);
        System.out.println(json);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String secertData = api_key + api_sha1 + package_name + formatter.format(curDate);
        String s = new String(Hex.encodeHex(DigestUtils.sha1(secertData)));
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = (new okhttp3.Request.Builder()).addHeader("key", api_key).addHeader("signature", s).addHeader("timestamp", formatter.format(curDate)).url(this.BEIKONGYUN_ACTION_URL).post(requestBody).build();
        System.out.println(this.BEIKONGYUN_ACTION_URL);
        System.out.println(api_sha1);
        System.out.println(request.body().toString());
        System.out.println(request.headers().toString());
        this.client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                OkHttpUtils.this.sendFailedCallback(call, new IOException("无法连接北控云服务器，请稍后再试"), callback);
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response != null) {
                    String resultType = response.body().string();
                    Log.i("AK认证", resultType);
                } else {
                    OkHttpUtils.this.sendFailedCallback(call, new IOException("此应用签名未经过北控云授权，请检查AK配置"), callback);
                }

            }
        });
    }

    public void getSecurity(final Context context, final String post_url, final OkHttpUtils.ResultCallback callback) {
        String api_key = ConfigUtil.getMetaDataFromAppication(context, "BEIKONGYUN_API_KEY");
        String package_name = ApplicationInformation.getPackageName(context);
        String api_sha1 = ApplicationInformation.getSingInfo(context.getApplicationContext(), package_name, "SHA1");
        ActionDataBean actionDataBean = this.getActionData(context, post_url);
        Gson gson = new Gson();
        String json = gson.toJson(actionDataBean);
        System.out.println(json);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String secertData = api_key + api_sha1 + package_name + formatter.format(curDate);
        String s = new String(Hex.encodeHex(DigestUtils.sha1(secertData)));
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = (new okhttp3.Request.Builder()).addHeader("key", api_key).addHeader("signature", s).addHeader("timestamp", formatter.format(curDate)).url(this.BEIKONGYUN_ACTION_URL).post(requestBody).build();
        System.out.println(request.body().toString());
        System.out.println(request.headers().toString());
        this.client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                OkHttpUtils.this.sendFailedCallback(call, new IOException("无法连接北控云服务器，请稍后再试"), callback);
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response != null) {
                    String resultType = response.body().string();
                    Log.i("AK认证", resultType);
                    if (resultType.equalsIgnoreCase("true")) {
                        OkHttpUtils.this.getAsyn(context, post_url, callback);
                    } else {
                        OkHttpUtils.this.sendFailedCallback(call, new IOException("此应用签名未经过北控云授权，请检查AK配置"), callback);
                    }
                }

            }
        });
    }

    public void postJsonSecurity(Context context, String post_url, Object object, String header_key, String header_vaule, OkHttpUtils.ResultCallback callback) {
        this.postJsonAsyn(context, post_url, object, header_key, encodeHeadInfo(header_vaule), callback);
        String api_key = ConfigUtil.getMetaDataFromAppication(context, "BEIKONGYUN_API_KEY");
        String package_name = ApplicationInformation.getPackageName(context);
        String api_sha1 = ApplicationInformation.getSingInfo(context.getApplicationContext(), package_name, "SHA1");
        ActionDataBean actionDataBean = this.getActionData(context, post_url);
        Gson gson = new Gson();
        String json = gson.toJson(actionDataBean);
        System.out.println(json);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String secertData = api_key + api_sha1 + package_name + formatter.format(curDate);
        String s = new String(Hex.encodeHex(DigestUtils.sha1(secertData)));
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        Request request = (new okhttp3.Request.Builder()).addHeader("key", api_key).addHeader("signature", s).addHeader("timestamp", formatter.format(curDate)).url(this.BEIKONGYUN_ACTION_URL).post(requestBody).build();
        assert request.body() != null;
        System.out.println(request.body().toString());
        System.out.println(request.headers().toString());

        this.client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {

            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response != null) {
                    String resultType = response.body().string();
                    Log.i("AK认证", resultType);
                    if (resultType.equalsIgnoreCase("true")) {
                    }
                }

            }
        });
    }

    private static String encodeHeadInfo(String headInfo) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;

        for(int length = headInfo.length(); i < length; ++i) {
            char c = headInfo.charAt(i);
            if (c > 31 && c < 127) {
                stringBuffer.append(c);
            } else {
                stringBuffer.append(String.format("\\u%04x", Integer.valueOf(c)));
            }
        }

        return stringBuffer.toString();
    }

    public interface ResultCallback {
        void onError(Request var1, Exception var2);

        void onResponse(Response var1) throws IOException;
    }
}
