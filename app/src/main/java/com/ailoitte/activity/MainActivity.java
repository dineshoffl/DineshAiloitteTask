package com.ailoitte.activity;

import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ailoitte.R;
import com.ailoitte.adapter.MyAdapter;
import com.ailoitte.helper.Application;
import com.ailoitte.helper.UtilsDefault;
import com.ailoitte.helper.ZoomOutPageTransformer;
import com.ailoitte.models.ModelPhotos;
import com.ailoitte.retrofit.API;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Inject
    API api;
    ViewPager viewPager;
    TextView name,story;
    List<ModelPhotos.Photo>photo=new ArrayList<>();
    String TAG="MainActivity";
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Application.getContext().getComponent().inject(this);
        viewPager=findViewById(R.id.pager);
        name=findViewById(R.id.username);
        story=findViewById(R.id.tv_story_name);
        viewPager.addOnPageChangeListener(this);
        imageView=findViewById(R.id.user_image);
        getPhotos();
    }

    @Override
    protected int getlayout() {
        return R.layout.activity_main;
    }

    public void getPhotos(){
        if (!UtilsDefault.isOnline()){
            alert("No internet connection");
            return;

        }

        shwProgress();
        api.getPhotos("10","DEMO_KEY").enqueue(new Callback<ModelPhotos>() {
            @Override
            public void onResponse(Call<ModelPhotos> call, Response<ModelPhotos> response) {
                hideprogress();


                if (response.body()!=null){
                    ModelPhotos modelPhotos=response.body();
                    if (modelPhotos.getPhotos()!=null&&modelPhotos.getPhotos().size()!=0){
                        photo.addAll(modelPhotos.getPhotos());
                        MyAdapter adapter=new MyAdapter(MainActivity.this,photo);
                        viewPager.setAdapter(adapter);
                        viewPager.setPageTransformer(false, new ZoomOutPageTransformer());
                        try {
                            name.setText(photo.get(0).getCamera().getName());
                            story.setText(photo.get(0).getCamera().getFull_name());
                        }
                        catch (Exception e){
                            Log.d(TAG, "onPageScrolled: "+"data null");
                        }

                    }

                }
                else {
                    Toast.makeText(MainActivity.this, getString(R.string.server_error), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ModelPhotos> call, Throwable t) {
                hideprogress();

                Toast.makeText(MainActivity.this, getString(R.string.server_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try {
            name.setText(photo.get(position).getCamera().getName());
            story.setText(photo.get(position).getCamera().getFull_name());
        }
        catch (Exception e){
            Log.d(TAG, "onPageScrolled: "+"data null");
        }


    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
