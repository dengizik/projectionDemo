package com.lbs.data.demo.topic.util;

public class LbsOrderWhen {
    private String caseField;
    private String operation;
    private Object caseValue;

    public LbsOrderWhen() {}

    public LbsOrderWhen(String caseField, String operation, Object caseValue) {
        this.caseField = caseField;
        this.operation = operation;
        this.caseValue = caseValue;
    }

    public String getCaseField() {
        return caseField;
    }

    public void setCaseField(String caseField) {
        this.caseField = caseField;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getCaseValue() {
        return caseValue;
    }

    public void setCaseValue(Object caseValue) {
        this.caseValue = caseValue;
    }
}
