package com.example.hackernews.newsmodel;

import java.util.ArrayList;


public class ApiResponse {

    private ArrayList<NewsDataModel> hits = new ArrayList<>();
    private int page;
    private String query;
    private String processingTimeMS;
    private String params;
    private String exhaustiveNbHits;
    private String nbHits;
    private String hitsPerPage;
    private int nbPages;

    public ArrayList<NewsDataModel> getHits() {
        return hits;
    }

    public void setHits(ArrayList<NewsDataModel> hits) {
        this.hits = hits;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getProcessingTimeMS() {
        return processingTimeMS;
    }

    public void setProcessingTimeMS(String processingTimeMS) {
        this.processingTimeMS = processingTimeMS;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getExhaustiveNbHits() {
        return exhaustiveNbHits;
    }

    public void setExhaustiveNbHits(String exhaustiveNbHits) {
        this.exhaustiveNbHits = exhaustiveNbHits;
    }

    public String getNbHits() {
        return nbHits;
    }

    public void setNbHits(String nbHits) {
        this.nbHits = nbHits;
    }

    public String getHitsPerPage() {
        return hitsPerPage;
    }

    public void setHitsPerPage(String hitsPerPage) {
        this.hitsPerPage = hitsPerPage;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }
}