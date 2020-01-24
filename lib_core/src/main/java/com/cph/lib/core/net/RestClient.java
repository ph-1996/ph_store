package com.cph.lib.core.net;

import android.content.Context;

import com.cph.lib.core.net.callback.IError;
import com.cph.lib.core.net.callback.IFailure;
import com.cph.lib.core.net.callback.IRequest;
import com.cph.lib.core.net.callback.ISuccess;
import com.cph.lib.core.net.callback.RequestCallbacks;
import com.cph.lib.core.net.download.DownloadHandler;
import com.cph.lib.core.ui.CoreLoader;
import com.cph.lib.core.ui.LoderStyle;


import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by CPH on 2020/1/21
 */

public class RestClient {
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();

    private final String URL;
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;


     RestClient(String url,
                      WeakHashMap<String, Object> params,
                String downloadDir,
                String extension,
                String name,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                   File file,
                   Context context,
                LoderStyle loderStyle) {
        this.URL = url;
        this.EXTENSION = extension;
        this.DOWNLOAD_DIR = downloadDir;
        this.NAME = name;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.CONTEXT = context;
        this.FILE = file;
        this.LOADER_STYLE = loderStyle;

    }
    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void  request(HttpMethod method){
        RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }
        if(LOADER_STYLE!=null){
            CoreLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL,BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            case POST_RAW:
                call = service.postRaw(URL,BODY);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            default:
                break;
        }
        if (call!=null){
            call.enqueue(getRequestCallback());
        }
    }
    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }
    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }
    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }
    public final void PUT(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
                SUCCESS, FAILURE, ERROR)
                .handleDownload();
    }

}
