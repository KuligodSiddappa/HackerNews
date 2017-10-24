package com.example.hackernews;

import com.example.hackernews.mvp.HackerNewsModel;

import org.junit.Test;

/**
 * Unit tests for {@link HackerNewsModel}
 */
public class HackerNewsModelTest {


    @Test
    public void testQuery() {
        HackerNewsModel.getInstance().queryNews(null, -1);
    }

}