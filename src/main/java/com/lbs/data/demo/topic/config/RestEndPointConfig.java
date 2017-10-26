package com.lbs.data.demo.topic.config;

import java.util.ArrayList;
import java.util.List;

public class RestEndPointConfig {

    private List<RestEndPoint> restEndPoints = new ArrayList<>();

    public List<RestEndPoint> getRestEndPoints() {
        return restEndPoints;
    }

    public void setRestEndPoints(List<RestEndPoint> restEndPoints) {
        this.restEndPoints = restEndPoints;
    }
}
