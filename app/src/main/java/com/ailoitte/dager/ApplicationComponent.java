package com.ailoitte.dager;



import com.ailoitte.activity.MainActivity;
import com.ailoitte.helper.ShoppingApplication;
import com.ailoitte.retrofit.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface ApplicationComponent {
    void inject(ShoppingApplication application);
    Retrofit retrofit();
    void inject(MainActivity mainActivity);



}
