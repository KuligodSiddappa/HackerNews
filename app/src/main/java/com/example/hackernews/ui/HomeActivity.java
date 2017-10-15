package com.example.hackernews.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hackernews.R;
import com.example.hackernews.mvp.HackerNewsContract;
import com.example.hackernews.mvp.HackerNewsModel;
import com.example.hackernews.mvp.HackerNewsPresenter;
import com.example.hackernews.ui.adapter.HackerNewsAdapter;
import com.example.hackernews.ui.adapter.ItemClickEvent;

public class HomeActivity extends AppCompatActivity implements HackerNewsContract.ViewUpdates,
        ItemClickEvent{
    private HackerNewsModel mModel;
    private HackerNewsPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private HackerNewsAdapter mAdapter;


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

        setupMvp();

        mRecyclerView = (RecyclerView) findViewById(R.id.hacker_news_list);
        mAdapter = new HackerNewsAdapter();
        mAdapter.registerForItemClick(this);

    }

    @Override
    public void init() {

    }

    @Override
    public void onItemClick() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tearDownMvp();
    }
}
