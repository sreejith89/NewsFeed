package com.example.sreejiths.newsfeed.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.sreejiths.newsfeed.R;

public class BaseActivity extends AppCompatActivity {

    public ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        dialog = new ProgressDialog(BaseActivity.this);
    }

    public void showProgressDialog(String title, String message) {
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.show();
    }

    public void hideProgressDialog() {
        dialog.dismiss();
    }

    public boolean checkInternetConnection() {

        ConnectivityManager cm =
                (ConnectivityManager) BaseActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
