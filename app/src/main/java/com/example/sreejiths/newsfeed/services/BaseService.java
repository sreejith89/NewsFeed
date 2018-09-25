package com.example.sreejiths.newsfeed.services;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public abstract class BaseService extends Application {

    private static BaseService mInstance;
    private RequestQueue mRequestQueue;
    public Context context;

    public abstract void onSuccess(JSONObject object);
    public abstract void onFailure(VolleyError object);

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static synchronized BaseService getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

    public void sendRequest(String url) {
        RequestQueue queue =  getRequestQueue();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("JSONResponse " + response.toString());
                onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Failure " + error.toString());
                onFailure(error);
            }
        });
        queue.add(jsObjRequest);
    }
}
