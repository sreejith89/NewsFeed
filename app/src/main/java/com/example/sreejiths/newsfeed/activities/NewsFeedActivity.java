package com.example.sreejiths.newsfeed.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.sreejiths.newsfeed.R;
import com.example.sreejiths.newsfeed.adapter.NewsFeedAdapter;
import com.example.sreejiths.newsfeed.model.News;
import com.example.sreejiths.newsfeed.services.BaseService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class NewsFeedActivity extends BaseActivity {

    private ArrayList<News> alNews;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        listView = findViewById(R.id.list_view);
        getJsonData();
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

    private void getJsonData() {
        showProgressDialog("News Feed", "loading");
        BaseService baseService = new BaseService() {
            @Override
            public void onSuccess(JSONObject object) {
                parseJsonObject(object);
                listView.setAdapter(new NewsFeedAdapter(NewsFeedActivity.this,
                        alNews));
                hideProgressDialog();
            }

            @Override
            public void onFailure(VolleyError object) {
                Toast.makeText(this, "Request Failed", Toast.LENGTH_SHORT).show();
                hideProgressDialog();

            }
        };
        baseService.setContext(NewsFeedActivity.this);
        baseService.sendRequest(BASE_URL);
    }

    private void refresh() {
        getJsonData();
    }

    private void parseJsonObject(JSONObject object) {
        try {

            String title;
            String description;
            String imageHref;
            JSONObject jsonObject;
            alNews = new ArrayList<News>();
            String mainTitle = object.getString("title");
            getSupportActionBar().setTitle(mainTitle);
            JSONArray jsonArray = object.getJSONArray("rows");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = (JSONObject) jsonArray.get(i);
                title = jsonObject.getString("title");
                description = jsonObject.getString("description");
                imageHref = jsonObject.getString("imageHref");
                alNews.add(new News(title, description, imageHref));
            }
            System.out.println("Array Count " + alNews.size());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
