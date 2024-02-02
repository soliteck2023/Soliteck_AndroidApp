package com.example.api_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CustomAdpter extends BaseAdapter {
    private Context context;
    private final String[] mobileValues;
//    private TextView t;


    public CustomAdpter(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }


    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        gridView = inflater.inflate(R.layout.showlist_item, null);
        ImageView imageView = (ImageView) gridView
                .findViewById(R.id.addimage1);
        String mobile = mobileValues[position];

        if (mobile.equals("Windows")){
            imageView.setImageResource(R.drawable.pravasi_divas_9th_jan);
        } else if (mobile.equals("ios")) {
            imageView.setImageResource(R.drawable.world_braille_day);
        }
        return gridView;
    }
//    public CustomAdpter(Context context) {
//        this.context = context;
//    }
//    public int[] imagegif = {
//            R.drawable.pravasi_divas_9th_jan,
//            R.drawable.world_braille_day
//    };
//
//    @Override
//    public int getCount() {
//        return imagegif.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return imagegif[position];
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView = new ImageView(context);
//        Glide.with(context).load(imagegif[position]).centerCrop().into(imageView);
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(1200,400));
//        return imageView;
//    }
}
