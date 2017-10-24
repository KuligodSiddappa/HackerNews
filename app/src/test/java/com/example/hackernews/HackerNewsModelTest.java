package com.example.hackernews;

import com.example.hackernews.mvp.HackerNewsModel;
import com.example.hackernews.network.EndApi;
import com.example.hackernews.network.IApiEvents;
import com.example.hackernews.newsmodel.ApiResponse;
import com.example.hackernews.newsmodel.IHackerNewsEvents;
import com.example.hackernews.newsmodel.NewsDataModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link HackerNewsModel}
 */
public class HackerNewsModelTest {
    @Mock
    Call<ApiResponse> call;
    @Mock
    Callback callback;
    @Mock
    IApiEvents apiEvents;
    @Mock
    IHackerNewsEvents presenterEvent;

    private HackerNewsModel mModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mModel = HackerNewsModel.getInstance();

        mModel.registerPresenter(presenterEvent);
    }

    @Test
    public void testQuerySuccess() {
        // On Mocking success response
        final ApiResponse response = new ApiResponse();
        response.setHits(new ArrayList<NewsDataModel>());
        response.setPage(1);
        response.setNbPages(5);

        when(apiEvents.loadData(anyString(), anyInt())).thenReturn(call);
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<ApiResponse> callback = (Callback<ApiResponse>) invocation.getArguments()[0];

                callback.onResponse(call, Response.success(response));

                return null;
            }
        }).when(call).enqueue(any(Callback.class));
        mModel.setApiEvents(apiEvents);
        mModel.queryNews(EndApi.POLITICS, 1);

        verify(presenterEvent).onNewsResult(response.getHits());
        assertEquals(response.getPage(), mModel.getCurrentPage());
        assertEquals(response.getNbPages(), mModel.getTotalPages());
        assertEquals(EndApi.POLITICS, mModel.getCurrentCategory());
    }

    @Test
    public void testQueryError() {
        // On failure response
        when(apiEvents.loadData(anyString(), anyInt())).thenReturn(call);
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<ApiResponse> callback = (Callback<ApiResponse>) invocation.getArguments()[0];

                callback.onFailure(call, new IOException("Invalid Response"));
                return null;
            }
        }).when(call).enqueue(any(Callback.class));
        mModel.setApiEvents(apiEvents);
        mModel.queryNews(EndApi.SPORTS, 2);

        verify(presenterEvent).onFailure(anyString());
        assertEquals(EndApi.SPORTS, mModel.getCurrentCategory());
    }

    @Test(expected = NullPointerException.class)
    public void testQueryWithNullCategory() {
        mModel.queryNews(null, 5);
    }

    @After
    public void tearDown() {
        mModel.deregisterPresenter(presenterEvent);
    }
}