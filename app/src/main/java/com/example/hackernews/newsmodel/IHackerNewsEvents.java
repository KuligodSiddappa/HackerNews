package com.example.hackernews.newsmodel;


import java.util.ArrayList;

public interface IHackerNewsEvents {

    void onNewsResult(ArrayList<NewsDataModel> newsDataModels);

    void onFailure(String messsage);
}
