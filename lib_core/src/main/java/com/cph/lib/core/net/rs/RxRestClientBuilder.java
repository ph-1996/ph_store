package com.cph.lib.core.net.rs;

import android.content.Context;

import com.cph.lib.core.net.RestClient;
import com.cph.lib.core.net.RestCreator;
import com.cph.lib.core.net.callback.IError;
import com.cph.lib.core.net.callback.IFailure;
import com.cph.lib.core.net.callback.IRequest;
import com.cph.lib.core.net.callback.ISuccess;
import com.cph.lib.core.ui.LoderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by CPH on 2020/1/21
 */
public class RxRestClientBuilder {
    private  static final WeakHashMap<String,Object> mParams = RestCreator.getParams();

    private  String mUrl = null;
    private  IRequest mIRequest = null;
    private  RequestBody mBody = null;
    private Context mContext = null;
    private File mFile = null;
    private LoderStyle mLoderStyle = null;
    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

   public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }







    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoderStyle style){
        this.mContext=context;
        this.mLoderStyle = style;
        return this;
    }
    public final RxRestClientBuilder loader(Context context){
        this.mContext=context;
        this.mLoderStyle =LoderStyle.BallClipRotatePulseIndicator;
        return this;
    }

 /*  public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }*/

   /*public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }*/



    public final RxRestClient build() {
        return new RxRestClient(mUrl,mParams,
                mIRequest,mBody,mFile,mContext,mLoderStyle);
    }
}
