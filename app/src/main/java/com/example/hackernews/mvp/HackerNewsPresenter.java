package com.example.hackernews.mvp;

import com.example.hackernews.Utils;

import java.lang.ref.WeakReference;


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
    public void onSportsNews() {

    }

    @Override
    public void onBollywoodNews() {

    }

    @Override
    public void onPoliticsNews() {

    }

    @Override
    public void onArtNews() {

    }
}
