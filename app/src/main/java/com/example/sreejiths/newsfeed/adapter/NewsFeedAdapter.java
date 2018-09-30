package com.example.sreejiths.newsfeed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sreejiths.newsfeed.R;
import com.example.sreejiths.newsfeed.model.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsFeedAdapter extends BaseAdapter {

    private Context context;
    LayoutInflater layoutInflater;
    private ArrayList<News> alNews;

    public NewsFeedAdapter (Context context, ArrayList<News> alNews) {
        this.context = context;
        this.alNews = alNews;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return alNews.size();
    }

    @Override
    public Object getItem(int position) {
        return alNews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvDescription = convertView.findViewById(R.id.tv_description);
            holder.ivIcon = convertView.findViewById(R.id.iv_icon);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        News newsObj = (News) getItem(position);
        holder.tvTitle.setText(newsObj.getTitle());
        holder.tvDescription.setText(newsObj.getDescription());
        loadImageFromUrl(newsObj.imageHref, holder.ivIcon);

        return convertView;
    }

    private void loadImageFromUrl(String url, ImageView imageView) {
        Picasso.get().load(url).placeholder(R.drawable.ic_error_outline).into(imageView);

    }

    public static class ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        ImageView ivIcon;
    }
}


