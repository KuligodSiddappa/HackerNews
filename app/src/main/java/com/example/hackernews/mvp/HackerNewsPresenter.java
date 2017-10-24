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
        mViewUpdates.updateNews(newsDataModels);
    }

    @Override
    public void onFailure(String messsage) {
        mViewUpdates.showError(messsage);
    }

    @Override
    public void onButtonClick(int buttonType) {
        switch (buttonType) {
            case Utils.SPORTS:
                queryNews(EndApi.SPORTS, Utils.DEFAULT_PAGE);
                break;
            case Utils.BOLLYWOOD:
                queryNews(EndApi.BOLLYWOOD, Utils.DEFAULT_PAGE);
                break;
            case Utils.POLITICS:
                queryNews(EndApi.POLITICS, Utils.DEFAULT_PAGE);
                break;
            case Utils.ART:
                queryNews(EndApi.ARTS, Utils.DEFAULT_PAGE);
                break;
        }

    }

    @Override
    public void start() {
        mViewUpdates.init();
    }

    @Override
    public int getTotalPages() {
        return mModel.getTotalPages();
    }

    @Override
    public int getCurrentPage() {
        return mModel.getCurrentPage();
    }

    @Override
    public boolean isPageLoading() {
        return mModel.isPageLoading();
    }

    @Override
    public String getCurrentCategory() {
        return mModel.getCurrentCategory();
    }

    @Override
    public void queryNews(String category, int page) {
        mModel.queryNews(category, page);
    }
}
