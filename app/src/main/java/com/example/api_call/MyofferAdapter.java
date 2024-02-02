package com.example.api_call;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyofferAdapter extends RecyclerView.Adapter<MyofferAdapter.ImageViewHolder>{

    List<add> list= new ArrayList<>();

    List<DataItem>dataItems = new ArrayList<>();

    Context context;

    public MyofferAdapter(List<DataItem> dataItems) {
        this.list = list;
        this.dataItems = dataItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyofferAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addvertiselistpage, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyofferAdapter.ImageViewHolder holder, int position) {

        DataItem dataItem = dataItems.get(position);
        String newsPath =  dataItem.getNewsPath();
        byte[] decodedBytes = Base64.decode(dataItem.getNewsPath(),Base64.DEFAULT);
        //below glide line working
        Glide.with(context).load(decodedBytes).into(holder.imageView);

//        Glide.with(context).asGif().load(decodedBytes).into(holder.imageView);
//        int imageResource = Integer.parseInt(list.get(position).getGifData());
//        holder.imageView.setImageResource(imageResource);


//        if (bannerList != null && position < bannerList.size()) {
//            DataItem dataItem = bannerList.get(position);
//            String newsPath = dataItem.getNewsPath();
//            byte[] decodedBytes = Base64.decode(newsPath, Base64.DEFAULT);
//            Glide.with(context)
//                    .load(decodedBytes)
//                    .into(holder.imageView);
//            // Your existing logic for binding data
//        }



//        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
//        holder.imageView.setImageBitmap(bitmap);
//        Glide.with(context)
//                .asGif() // Specify that the resource is a GIF
//                .load(newsPath)
//                .into(holder.imageView);
//        holder.imageView.setImageResource(Integer.parseInt(dataItem.getNewsPath()));

//        int imageResource = Integer.parseInt(dataItems1.get(position).getNewsPath());
//        holder.imageView.setImageResource(imageResource);

    }

//    private Bitmap decodeBencodedImage(String bencodedImage) {
//        byte[] decodedBytes = Base64.decode(bencodedImage, Base64.DEFAULT);
//        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.addimage1);
        }
    }
}
