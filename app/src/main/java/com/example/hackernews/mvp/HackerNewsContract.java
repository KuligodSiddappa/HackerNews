package com.example.hackernews.mvp;


import com.example.hackernews.newsmodel.IHackerNewsEvents;

/**
 * Holder of all the interfaces which help in establishing the contract between the Model, View and
 * Presenter. Each of these interfaces provide the required methods to be implemented by Model, View
 * and Presenter.
 */
public interface HackerNewsContract {

    /**
     * Interface to be implemented by the presenter.
     * Provides operations offered to View to communicate with Presenter.
     * Processes user interactions, sends data requests to Model, etc.
     */
    interface ViewEvents {

    }


    /**
     * Interface to be implemented by the presenter.
     * Provides required Presenter methods available to Model.
     */
    interface ModelEvents extends IHackerNewsEvents{

    }

    /**
     * Interface to be implemented by the View.
     * Provides required View methods available to Presenter.
     * A passive layer, responsible to show data/update UI
     * and receive user interactions
     */
    interface ViewUpdates {

        void init();
    }


    /**
     * Interface to be implemented by the model.
     * Operations offered to Model to communicate with Presenter.
     * Handles all data business logic.
     */
    interface ModelUpdates {

    }
}
