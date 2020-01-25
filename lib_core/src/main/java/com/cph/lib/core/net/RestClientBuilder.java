package com.cph.lib.core.net;

import android.content.Context;

import com.cph.lib.core.net.callback.IError;
import com.cph.lib.core.net.callback.IFailure;
import com.cph.lib.core.net.callback.IRequest;
import com.cph.lib.core.net.callback.ISuccess;
import com.cph.lib.core.ui.loader.LoderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by CPH on 2020/1/21
 */
public class RestClientBuilder {
    private  static final WeakHashMap<String,Object> mParams = RestCreator.getParams();

    private  String mUrl = null;
    private  IRequest mIRequest = null;
    private  ISuccess mISuccess = null;
    private  IFailure mIFailure = null;
    private  IError mIError = null;
    private  RequestBody mBody = null;
    private Context mContext = null;
    private File mFile = null;
    private LoderStyle mLoderStyle = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;
    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

   public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

   public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

   public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }
    public final RestClientBuilder loader(Context context,LoderStyle style){
        this.mContext=context;
        this.mLoderStyle = style;
        return this;
    }
    public final RestClientBuilder loader(Context context){
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



    public final RestClient build() {
        return new RestClient(mUrl,mParams,mDownloadDir,mExtension,mName,
                mIRequest, mISuccess, mIFailure,
                mIError, mBody,mFile,mContext,mLoderStyle);
    }
}
