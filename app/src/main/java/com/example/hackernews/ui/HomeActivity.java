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
import com.example.hackernews.network.BaseApi;
import com.example.hackernews.network.EndApi;
import com.example.hackernews.network.IApiEvents;
import com.example.hackernews.newsmodel.NewsDataModel;
import com.example.hackernews.ui.adapter.HackerNewsAdapter;
import com.example.hackernews.ui.adapter.ItemClickEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private LinearLayoutManager mLinearLayoutManager;

    private void setupMvp() {
        //create the model
        mModel = HackerNewsModel.getInstance();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_URl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        IApiEvents apiEvents = retrofit.create(IApiEvents.class);

        mModel.setApiEvents(apiEvents);

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
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new HackerNewsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.registerForItemClick(this);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int visibleItems = recyclerView.getChildCount();
                int totalItems = mLinearLayoutManager.getItemCount();
                int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();

                if (!mPresenter.isPageLoading() && (totalItems - visibleItems) <= (firstVisibleItemPosition + 5)
                        && mPresenter.getCurrentPage() < mPresenter.getTotalPages()) {
                    mPresenter.queryNews(mPresenter.getCurrentCategory(), mPresenter.getCurrentPage() + 1);
                }
            }
        });

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
        if (mAdapter != null && news != null) {
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
                if (mPresenter.getCurrentCategory() != null
                        && !mPresenter.getCurrentCategory().equals(EndApi.SPORTS))
                    mAdapter.clear();
                mPresenter.onButtonClick(Utils.SPORTS);
            }
            break;

            case R.id.Button_Bollywood: {
                if (mPresenter.getCurrentCategory() != null
                        && !mPresenter.getCurrentCategory().equals(EndApi.BOLLYWOOD))
                    mAdapter.clear();
                mPresenter.onButtonClick(Utils.BOLLYWOOD);
            }
            break;

            case R.id.Button_Politics: {
                if (mPresenter.getCurrentCategory() != null
                        && !mPresenter.getCurrentCategory().equals(EndApi.POLITICS))
                    mAdapter.clear();
                mPresenter.onButtonClick(Utils.POLITICS);
            }
            break;

            case R.id.Button_Art: {
                if (mPresenter.getCurrentCategory() != null
                        && !mPresenter.getCurrentCategory().equals(EndApi.ARTS))
                    mAdapter.clear();
                mPresenter.onButtonClick(Utils.ART);
            }
            break;
        }
    }

}
