package com.example.sreejiths.newsfeed.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sreejiths.newsfeed.R;
import com.example.sreejiths.newsfeed.model.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.NewsViewHolder> {

    private ArrayList<News> alNews;

    public NewsFeedAdapter (ArrayList<News> alNews) {
        this.alNews = alNews;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(itemView);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = alNews.get(position);
        holder.tvTitle.setText(news.getTitle());
        holder.tvDescription.setText(news.getDescription());
        if(!isImageNull(news.getImageHref())) {
            holder.ivIcon.setVisibility(View.VISIBLE);
            loadImageFromUrl(news.getImageHref(), holder.ivIcon);
        } else holder.ivIcon.setVisibility(View.GONE);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return alNews.size();
    }

    private boolean isImageNull(String imageUrl) {
        if(imageUrl.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    private void loadImageFromUrl(String url, ImageView imageView) {
        Picasso.get().load(url).into(imageView);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvDescription;
        ImageView ivIcon;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }
}


