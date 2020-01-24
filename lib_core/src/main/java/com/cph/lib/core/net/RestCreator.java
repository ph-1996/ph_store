package com.cph.lib.core.net;

import android.util.Log;
import android.widget.TextView;

import com.cph.lib.core.app.ConfigType;
import com.cph.lib.core.app.Configurator;
import com.cph.lib.core.app.Core;
import com.cph.lib.core.net.rs.RxRestService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by CPH on 2020/1/21
 */
public class RestCreator {

  private static final class ParamsHolder{
        private static final WeakHashMap<String,Object> PARAMS  = new WeakHashMap<>();
    }
    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }
    private static final class OKHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Core.getConfiguration(ConfigType.INTERCEPTOR);
        private static OkHttpClient.Builder addInterceptor(){
            if(INTERCEPTORS !=null && !INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor:INTERCEPTORS){
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    private static final class RetrofitHolder{
        private static final String BASE_URL = (String) Core.getConfigurations().get(ConfigType.API_HOST.name());

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl("http://news.baidu.com/")
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
      //  private static final RestService REST_SERVICE  = new Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(ScalarsConverterFactory.create()).build().create(RestService.class);
    }

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }


    //音就Rxjava
    private static final class RxRestServiceHolder{
        private static final RxRestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
        //  private static final RestService REST_SERVICE  = new Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(ScalarsConverterFactory.create()).build().create(RestService.class);
    }

    public static RxRestService getRxRestService(){
        return RxRestServiceHolder.REST_SERVICE;
    }
}
