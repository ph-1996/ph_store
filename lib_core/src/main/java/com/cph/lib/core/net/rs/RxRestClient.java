package com.cph.lib.core.net.rs;

import android.content.Context;

import com.cph.lib.core.net.HttpMethod;
import com.cph.lib.core.net.RestCreator;
import com.cph.lib.core.net.callback.IRequest;
import com.cph.lib.core.ui.loader.CoreLoader;
import com.cph.lib.core.ui.loader.LoderStyle;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by CPH on 2020/1/21
 */

public class RxRestClient {
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();

    private final String URL;
    private final IRequest REQUEST;
    private final RequestBody BODY;
    private final LoderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;


     RxRestClient(String url,
                  WeakHashMap<String, Object> params,
                  IRequest request,
                  RequestBody body,
                  File file,
                  Context context,
                  LoderStyle loderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.BODY = body;
        this.CONTEXT = context;
        this.FILE = file;
        this.LOADER_STYLE = loderStyle;

    }
    public static RxRestClientBuilder builder(){
        return new RxRestClientBuilder();
    }

    private Observable<String>  request(HttpMethod method){
        RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;
        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }
        if(LOADER_STYLE!=null){
            CoreLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method){
            case GET:
                observable = service.get(URL,PARAMS);
                break;
            case POST:
                observable = service.post(URL,PARAMS);
                break;
            case PUT:
                observable = service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL,BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = service.upload(URL, body);
                break;
            case POST_RAW:
                observable = service.postRaw(URL,BODY);
                break;
            case DELETE:
                observable = service.delete(URL,PARAMS);
                break;
            default:
                break;
        }
            return observable;
    }

    public final Observable<String> get(){
       return request(HttpMethod.GET);
    }
    public final Observable<String> post() {
        if (BODY == null) {
           return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
           return request(HttpMethod.POST_RAW);
        }
    }
    public final Observable<String> put() {
        if (BODY == null) {
           return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }
    public final Observable<String> PUT(){
        return request(HttpMethod.PUT);
    }
    public final Observable<String> delete(){
        return request(HttpMethod.DELETE);
    }
    public final Observable<ResponseBody> download() {
        final Observable<ResponseBody> responseBodyObservable = RestCreator.getRxRestService().download(URL,PARAMS);
        return responseBodyObservable;
    }

}
