package com.example.hackernews.network;

import com.example.hackernews.newsmodel.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApiEvents {

    @GET(EndApi.SEARCH)
    Call<ApiResponse> loadData(@Query("query") String query,@Query("page") int page);
}
