package com.example.hackernews;


import com.example.hackernews.mvp.HackerNewsContract;
import com.example.hackernews.mvp.HackerNewsPresenter;
import com.example.hackernews.network.EndApi;
import com.example.hackernews.newsmodel.NewsDataModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Unit tests for {@link HackerNewsPresenter}
 */
public class HackerNewsPresenterTest {
    private static HackerNewsPresenter mPresenterInstance;
    @Mock
    private HackerNewsContract.ViewUpdates mViewUpdates;
    @Mock
    private HackerNewsContract.ModelUpdates mModelUpdates;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPresenterInstance = new HackerNewsPresenter(mViewUpdates, mModelUpdates);
    }

    @Test
    public void testStart() {
        mPresenterInstance.start();
        verify(mViewUpdates).init();
    }

    @Test
    public void testButtonClickToQuery() {
        mPresenterInstance.onButtonClick(Utils.POLITICS);
        verify(mModelUpdates).queryNews(eq(EndApi.POLITICS), anyInt());

        mPresenterInstance.onButtonClick(-1);
        verifyZeroInteractions(mModelUpdates);
    }

    @Test(expected = NullPointerException.class)
    public void testNewsResultWithNull() {
        mPresenterInstance.onNewsResult(null);
        verifyZeroInteractions(mViewUpdates);
    }

    @Test
    public void testNewsResult(){
        mPresenterInstance.onNewsResult(new ArrayList<NewsDataModel>());
       verify(mViewUpdates).updateNews(any(ArrayList.class));
    }

    @After
    public void tearDown() {

    }

}
