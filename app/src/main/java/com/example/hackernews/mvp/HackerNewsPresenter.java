package com.example.hackernews.mvp;

import com.example.hackernews.Utils;
import com.example.hackernews.network.EndApi;
import com.example.hackernews.newsmodel.NewsDataModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


public class HackerNewsPresenter implements HackerNewsContract.ViewEvents,
        HackerNewsContract.ModelEvents {

    //View reference
    private WeakReference<HackerNewsContract.ViewUpdates> mView;
    //Model reference
    private HackerNewsContract.ModelUpdates mModel;
    private HackerNewsContract.ViewUpdates mViewUpdates;

    public HackerNewsPresenter(HackerNewsContract.ViewUpdates view, HackerNewsModel model) {
        mView = new WeakReference<>(Utils.checkNotNull(view, "View should not be null"));
        mViewUpdates = mView.get();
        mModel = Utils.checkNotNull(model, "Model Should not be null");
    }


    @Override
    public void onNewsResult(ArrayList<NewsDataModel> newsDataModels) {

    }

    @Override
    public void onButtonClick(int buttonType) {
        switch(buttonType){
            case Utils.SPORTS:
                mModel.queryNews(EndApi.SPORTS);
                break;
            case Utils.BOLLYWOOD:
                mModel.queryNews(EndApi.BOLLYWOOD);
                break;
            case Utils.POLITICS:
                mModel.queryNews(EndApi.POLITICS);
                break;
            case Utils.ART:
                mModel.queryNews(EndApi.ARTS);
                break;
        }

    }

    @Override
    public void start() {
        mViewUpdates.init();
    }
}
