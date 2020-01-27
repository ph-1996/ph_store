package com.cph.phstore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.core.net.RestClient;
import com.cph.lib.core.net.RestCreator;
import com.cph.lib.core.net.callback.IError;
import com.cph.lib.core.net.callback.IFailure;
import com.cph.lib.core.net.callback.ISuccess;
import com.cph.lib.core.net.rs.RxRestClient;
import com.cph.lib.core.net.rs.RxRestService;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by CPH on 2020/1/21
 */
public class ExampleDelegate extends CoreDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
           testRestClient();
       // onCallRxGet();
            Log.d("lxx","run" );
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://192.168.47.1:8090/myjson/moni.json")
               // .params("","")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response.toString(),Toast.LENGTH_LONG).show();
                        Log.d("lxx","successs");
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                    Log.d("lxx","failure");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.d("lxx","error");
                    }
                })
                .build()
                .get();
    }

    //TODO:测试Rxjava网络请求 测试1
    void onCallRxGet(){
        final String url = "http//index";
                final WeakHashMap<String,Object> params = new WeakHashMap<>();
        final Observable<String> observable = (Observable<String>) RestCreator.getRxRestService().get(url,params);
               observable .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(getContext(),s+111,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    //TODO:测试Rxjava 测试2
    private void onCallRxRestClient(){
        final String url = "http//index";
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
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
