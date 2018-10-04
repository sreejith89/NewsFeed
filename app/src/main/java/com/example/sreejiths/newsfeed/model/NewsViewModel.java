package com.example.sreejiths.newsfeed.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.sreejiths.newsfeed.services.BaseService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    public final String BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";

    private String TAG = NewsViewModel.class.getSimpleName();
    private MutableLiveData<List<News>> news;
    private Context cotext;


    public LiveData<List<News>> getNews(Context context) {
        if (news == null) {
            news = new MutableLiveData<List<News>>();
            this.cotext = context;
            loadNews();
        }
        return news;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }


    private void loadNews() {
        BaseService baseService = new BaseService() {

            @Override
            public void onSuccess(JSONObject object) {
                ArrayList<News> newsList = new ArrayList<>();
                newsList = parseJsonObject(object);
                news.setValue(newsList);
            }

            @Override
            public void onFailure(VolleyError object) {
                Log.d(TAG, "Request Failed " + object.toString());
                news.setValue(null);
            }
        };
        baseService.setContext(cotext);
        baseService.sendRequest(BASE_URL);
    }


    private ArrayList<News> parseJsonObject(JSONObject object) {
        ArrayList<News> alNews = new ArrayList<News>();
        try {
            String title;
            String description;
            String imageHref;
            JSONObject jsonObject;
            String mainTitle = object.getString("title");
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
        return alNews;
    }
}
