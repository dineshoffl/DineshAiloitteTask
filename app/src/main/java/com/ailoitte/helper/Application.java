package com.ailoitte.helper;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.ailoitte.dager.AppModule;
import com.ailoitte.dager.ApplicationComponent;
import com.ailoitte.dager.DaggerApplicationComponent;
import com.ailoitte.retrofit.RetrofitModule;


public class Application extends MultiDexApplication {

    public static final String TAG = Application.class
            .getSimpleName();
    private static Context context;
    private static Application mInstance;
    private ApplicationComponent mComponent;
    public static Application getContext() {
        return mInstance;
    }



    public static Context globalContext() {
        return context;
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }

    public static Resources getAppResources() {
        return context.getResources();
    }


    public static Context getGlobalContext() {
        // TODO Auto-generated method stub
        return context;
    }
    public ApplicationComponent getComponent() {
        return mComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        mInstance = this;
        context = null;
        context = getApplicationContext();
        //  intializeTwitter();
//
        mComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/",getContext()))
                .build();
        mComponent.inject(this);


//        TwitterConfig config = new TwitterConfig.Builder(this)
//                .logger(new DefaultLogger(Log.DEBUG))
//                .twitterAuthConfig(new TwitterAuthConfig("Qv17c7O34xPWkvvAPOXUiDDYH", "YQi9mD1bsWj1V323sH7mTPw0pEGdgU7Rau76dam8KCX35aKjtW"))
//                .debug(true)
//                .build();
//        Twitter.initialize(config);

//        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
//        String apiKey = Base64.decode(getAPIKey(), Base64.DEFAULT);


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }



    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
    public static Application from(@NonNull Context context) {
        return (Application) context.getApplicationContext();
    }

}
