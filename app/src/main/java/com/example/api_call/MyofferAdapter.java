package com.example.api_call;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyofferAdapter extends RecyclerView.Adapter<MyofferAdapter.ImageViewHolder>{

    List<add> list;

    public MyofferAdapter(List<add> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyofferAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addvertiselistpage, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyofferAdapter.ImageViewHolder holder, int position) {
        int imageResource = list.get(position).getImage();
        holder.imageView.setImageResource(imageResource);
    }

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
