package com.example.hackernews.mvp;

import com.example.hackernews.network.BaseApi;
import com.example.hackernews.network.IApiEvents;
import com.example.hackernews.newsmodel.ApiResponse;
import com.example.hackernews.newsmodel.IHackerNewsEvents;
import com.example.hackernews.newsmodel.NewsDataModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HackerNewsModel implements HackerNewsContract.ModelUpdates {

    private static HackerNewsModel sModel;
    private Retrofit mRetrofit;
    private List<IHackerNewsEvents> mHackerNewsEventsList = new ArrayList<>();


    private HackerNewsModel() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_URl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
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

    @Override
    public void queryNews(String category) {
        Call<ApiResponse> call = mRetrofit.create(IApiEvents.class).loadData(category);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        onData(response.body().getHits());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                if (t != null)
                    onFail(t.getMessage().toString());
            }
        });
    }

    private void onData(ArrayList<NewsDataModel> dataModels) {

        if (mHackerNewsEventsList != null && !mHackerNewsEventsList.isEmpty()) {
            for (IHackerNewsEvents events : mHackerNewsEventsList) {
                events.onNewsResult(dataModels);
            }
        }
    }

    private void onFail(String message) {
        if (mHackerNewsEventsList != null && !mHackerNewsEventsList.isEmpty()) {
            for (IHackerNewsEvents events : mHackerNewsEventsList) {
                events.onFailure(message);
            }
        }
    }
}
