package com.example.sreejiths.newsfeed.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sreejiths.newsfeed.R;

public class BaseActivity extends AppCompatActivity {

    public final String BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json" ;
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
}
