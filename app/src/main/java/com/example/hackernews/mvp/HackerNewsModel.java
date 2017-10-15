package com.example.hackernews.mvp;

import com.example.hackernews.newsmodel.IHackerNewsEvents;

import java.util.ArrayList;
import java.util.List;

public class HackerNewsModel implements HackerNewsContract.ModelUpdates {

    private static HackerNewsModel sModel;
    private List<IHackerNewsEvents> mHackerNewsEventsList = new ArrayList<>();


    private HackerNewsModel() {

    }

    /**
     * Creates singleton instance of this class
     *
     * @return HackerNewsModel instance
     */
    public static HackerNewsModel getInstance() {
        if (sModel == null) {
            synchronized (HackerNewsModel.class) {
                if (sModel == null) {
                    sModel = new HackerNewsModel();
                }
            }
        }
        return sModel;
    }

    /**
     * Registers multiple presenter instances for any model updates
     *
     * @param hackerNewsEvents particular presenter required to be registered for model updates
     */
    public void registerPresenter(IHackerNewsEvents hackerNewsEvents) {
        if (mHackerNewsEventsList != null) {
            if (!mHackerNewsEventsList.contains(hackerNewsEvents)) {
                mHackerNewsEventsList.add(hackerNewsEvents);
            }
        }
    }

    /**
     * Facilitates the presenter's request to unregister it from the model updates
     *
     * @param hackerNewsEvents particular presenter required to be unregistered from model updates
     */
    public void deregisterPresenter(IHackerNewsEvents hackerNewsEvents) {
        if (mHackerNewsEventsList != null && !mHackerNewsEventsList.isEmpty())
            mHackerNewsEventsList.remove(hackerNewsEvents);
    }
}
