package com.ailoitte.dager;

import android.content.Context;


import com.ailoitte.helper.Application;
import com.ailoitte.retrofit.API;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppModule {




    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }
    //provides dependencies (return application class objects)
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return app;
    }


    @Provides
    public API provideViduApiInterface(Retrofit retrofit) {
        return retrofit.create(API.class);
    }
}
