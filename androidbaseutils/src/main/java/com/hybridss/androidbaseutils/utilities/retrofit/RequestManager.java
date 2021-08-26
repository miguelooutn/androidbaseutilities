package com.hybridss.androidbaseutils.utilities.retrofit;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {
    private Retrofit.Builder retrofit;

    public RequestManager(String BASE_URL) {
        configureRequestManager(BASE_URL, 60, 10);
    }

    public RequestManager(String BASE_URL, int timeout) {
        configureRequestManager(BASE_URL, timeout, 10);
    }

    public RequestManager(String BASE_URL, long timeout, long readTimeout) {
        configureRequestManager(BASE_URL, timeout, readTimeout);
    }

    private void configureRequestManager(String BASE_URL, long connectTimeout, long readTimeout) {
        OkHttpClient okHttpClient = UnsafeOkHttpClient
                .getUnsafeOkHttpClient()
                .connectTimeout(connectTimeout != 0 ? connectTimeout : 60, TimeUnit.SECONDS)
                .readTimeout(readTimeout != 0 ? readTimeout : 10, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient);
    }

    public RequestManager(String BASE_URL, int timeOut, int readTimeOut, int writeTimeOut) {
        OkHttpClient okHttpClient = UnsafeOkHttpClient
                .getUnsafeOkHttpClient()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(readTimeOut, TimeUnit.SECONDS)
                .writeTimeout(writeTimeOut, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(BASE_URL)
                .client(okHttpClient);
    }

    public <T> T create(Class<T> service) {
        return retrofit.build().create(service);
    }

    public <T> T create(Class<T> service, String username, String password) {
        HttpLoggingInterceptor interceptorLogger = new HttpLoggingInterceptor();
        interceptorLogger.setLevel(HttpLoggingInterceptor.Level.BODY);
        String authToken = Credentials.basic(username, password);
        AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);
        OkHttpClient.Builder httpClient = UnsafeOkHttpClient
                .getUnsafeOkHttpClient();
        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor);
            httpClient.addInterceptor(interceptorLogger);
            retrofit.client(httpClient.build());
        }
        return retrofit.build().create(service);
    }
}
