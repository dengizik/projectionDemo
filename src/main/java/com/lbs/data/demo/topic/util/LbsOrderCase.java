package com.lbs.data.demo.topic.util;

public class LbsOrderCase {
    private LbsOrderWhen orderWhen;
    private String orderField;
    private Object orderVaLue;

    public LbsOrderCase() {}

    public LbsOrderCase(LbsOrderWhen orderWhen, String orderField, Object orderVaLue) {
        this.orderWhen = orderWhen;
        this.orderField = orderField;
        this.orderVaLue = orderVaLue;
    }

    public LbsOrderWhen getOrderWhen() {
        return orderWhen;
    }

    public void setOrderWhen(LbsOrderWhen orderWhen) {
        this.orderWhen = orderWhen;
    }

    public Object getOrderVaLue() {
        return orderVaLue;
    }

    public void setOrderVaLue(Object orderVaLue) {
        this.orderVaLue = orderVaLue;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }
}
