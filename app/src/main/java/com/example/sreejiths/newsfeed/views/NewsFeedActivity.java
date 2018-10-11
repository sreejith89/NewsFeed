package com.example.sreejiths.newsfeed.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sreejiths.newsfeed.R;
import com.example.sreejiths.newsfeed.connectionManager.ConnectivityReceiver;
import com.example.sreejiths.newsfeed.connectionManager.MyApplication;
import com.example.sreejiths.newsfeed.model.News;
import com.example.sreejiths.newsfeed.viewmodels.NewsViewModel;

import java.util.ArrayList;


public class NewsFeedActivity extends BaseActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recyclerView;
    private NewsFeedAdapter newsFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        recyclerView = findViewById(R.id.recycler_view);
        swipeContainer = findViewById(R.id.swipe_container);
        if(isConnectionAvailable()){
            Toast.makeText(NewsFeedActivity.this, "connected to internet", Toast.LENGTH_SHORT).show();
            getInfoAboutCanada();
        } else Toast.makeText(NewsFeedActivity.this, "not connected to internet", Toast.LENGTH_SHORT).show();

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getInfoAboutCanada();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    public boolean isConnectionAvailable() {
        return  ConnectivityReceiver.isConnected();
    }

    private void getInfoAboutCanada() {
        showProgressDialog("News Feed", "loading");
        NewsViewModel newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.getNews(NewsFeedActivity.this).observe(this,  news-> {
            if(news != null) {
                newsFeedAdapter = new NewsFeedAdapter((ArrayList<News>) news);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(newsFeedAdapter);
                swipeContainer.setRefreshing(false);
            }
            hideProgressDialog();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_feed_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_refresh){
            refresh();
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        getInfoAboutCanada();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(isConnected) Toast.makeText(NewsFeedActivity.this, "connected to internet", Toast.LENGTH_SHORT).show();
        else Toast.makeText(NewsFeedActivity.this, "not connected to internet", Toast.LENGTH_SHORT).show();
    }
}
