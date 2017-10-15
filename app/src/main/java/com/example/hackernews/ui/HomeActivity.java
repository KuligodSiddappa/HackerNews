package com.example.hackernews.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hackernews.R;
import com.example.hackernews.mvp.HackerNewsContract;
import com.example.hackernews.mvp.HackerNewsModel;
import com.example.hackernews.mvp.HackerNewsPresenter;

public class HomeActivity extends AppCompatActivity implements HackerNewsContract.ViewUpdates {

    private HackerNewsModel mModel;
    private HackerNewsPresenter mPresenter;

    private void setupMvp() {
        //create the model
        mModel = HackerNewsModel.getInstance();

        //create the presenter
        mPresenter = new HackerNewsPresenter(this, mModel);

        //register for model events
        mModel.registerPresenter(mPresenter);
    }

    private void tearDownMvp() {
        mModel.deregisterPresenter(mPresenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init() {

    }
}
