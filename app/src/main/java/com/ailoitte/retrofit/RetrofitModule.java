package com.ailoitte.retrofit;

import android.content.Context;

import androidx.annotation.Nullable;


import com.ailoitte.BuildConfig;
import com.ailoitte.helper.Application;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule
{
    String mBaseUrl;
    Context context;
    public RetrofitModule(String baseUrl, Application context) {
        this.mBaseUrl = baseUrl;
        this.context= context;


    }

    @Provides
    @Singleton
    Interceptor provideInterceptor() {
        Interceptor interceptorAPI = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = null;

                request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();
                Response response = chain.proceed(request);

                return response;
            }
        };
        return interceptorAPI;
    }


    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.interceptors().add(interceptor);
        okHttpBuilder.authenticator(new Authenticator() {
            @Nullable
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                return response.request().newBuilder()
                        .build();
            }
        });
        okHttpBuilder.readTimeout(60, TimeUnit.SECONDS);
        okHttpBuilder.connectTimeout(60, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpBuilder.interceptors().add(logging);
        }
        OkHttpClient client = okHttpBuilder.build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit =
                new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                        .baseUrl(mBaseUrl)
                        .client(okHttpClient)
                        .build();
        return retrofit;
    }

}
