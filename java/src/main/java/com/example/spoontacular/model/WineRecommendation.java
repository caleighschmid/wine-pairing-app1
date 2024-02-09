package com.example.spoontacular.model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class WineRecommendation {

    private List<String> wines = new ArrayList<>();

    private String recBlurb;

    private Wine specificRec;

    public WineRecommendation() {
    }

    public List<String> getWines() {
        return wines;
    }

    public void setWines(List<String> wines) {
        this.wines = wines;
    }

    public String getRecBlurb() {
        return recBlurb;
    }

    public void setRecBlurb(String recBlurb) {
        this.recBlurb = recBlurb;
    }

    public Wine getSpecificRec() {
        return specificRec;
    }

    public void setSpecificRec(Wine specificRec) {
        this.specificRec = specificRec;
    }
}
