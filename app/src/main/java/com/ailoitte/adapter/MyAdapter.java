package com.ailoitte.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.ailoitte.R;
import com.ailoitte.models.ModelPhotos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by $Dinesh on 12/28/2019.
 */
public class MyAdapter extends PagerAdapter {

    private List<ModelPhotos.Photo> images;
    private LayoutInflater inflater;
    private Context context;

    public MyAdapter(Context context, List<ModelPhotos.Photo> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }
    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_slide, collection, false);
        ImageView imageView=layout.findViewById(R.id.image);
        Picasso.with(context).load(images.get(position).getImg_src()).into(imageView);
        Log.d("imagesss", "instantiateItem: "+images.get(position).getImg_src());
        collection.addView(layout);
        return layout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}