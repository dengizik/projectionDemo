package com.lbs.data.demo.topic.util;

import org.springframework.data.domain.Sort;

import java.util.List;

public class LbsQueryOrder {

    private List<LbsOrderCase> orderCases;
    private Sort.Direction orderDirection;

    public List<LbsOrderCase> getOrderCases() {
        return orderCases;
    }

    public void setOrderCases(List<LbsOrderCase> orderCases) {
        this.orderCases = orderCases;
    }

    public Sort.Direction getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(Sort.Direction orderDirection) {
        this.orderDirection = orderDirection;
    }
}
