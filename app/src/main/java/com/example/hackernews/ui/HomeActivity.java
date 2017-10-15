package com.example.hackernews.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hackernews.R;
import com.example.hackernews.Utils;
import com.example.hackernews.mvp.HackerNewsContract;
import com.example.hackernews.mvp.HackerNewsModel;
import com.example.hackernews.mvp.HackerNewsPresenter;
import com.example.hackernews.newsmodel.NewsDataModel;
import com.example.hackernews.ui.adapter.HackerNewsAdapter;
import com.example.hackernews.ui.adapter.ItemClickEvent;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements HackerNewsContract.ViewUpdates,
        ItemClickEvent, View.OnClickListener {
    private static final String TAG = "HomeActivity";
    private HackerNewsModel mModel;
    private HackerNewsPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private HackerNewsAdapter mAdapter;
    private Button mSportsButton;
    private Button mBollywoodButton;
    private Button mPoliticsButton;
    private Button mArtsButton;


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

        mSportsButton = (Button) findViewById(R.id.Button_Sports);
        mBollywoodButton = (Button) findViewById(R.id.Button_Bollywood);
        mPoliticsButton = (Button) findViewById(R.id.Button_Politics);
        mArtsButton = (Button) findViewById(R.id.Button_Art);

        mRecyclerView = (RecyclerView) findViewById(R.id.hacker_news_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new HackerNewsAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.registerForItemClick(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void init() {
        mSportsButton.setOnClickListener(this);
        mBollywoodButton.setOnClickListener(this);
        mPoliticsButton.setOnClickListener(this);
        mArtsButton.setOnClickListener(this);
    }

    @Override
    public void updateNews(ArrayList<NewsDataModel> news) {
        if (mAdapter != null && news != null){
            Toast.makeText(this,"Items : "+news.size(),Toast.LENGTH_LONG).show();
            mAdapter.updateData(news);
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, " " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tearDownMvp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Button_Sports: {
                mPresenter.onButtonClick(Utils.SPORTS);
            }
            break;

            case R.id.Button_Bollywood: {
                mPresenter.onButtonClick(Utils.BOLLYWOOD);
            }
            break;

            case R.id.Button_Politics: {
                mPresenter.onButtonClick(Utils.POLITICS);
            }
            break;

            case R.id.Button_Art: {
                mPresenter.onButtonClick(Utils.ART);
            }
            break;
        }
    }
}
