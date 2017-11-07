package com.lbs.data.demo.topic.repository.spec;

public class LbsSearchCriteria {

    private String key;
    private String[] keys;
    private String operation;
    private Object value;
    private Object[] values;

    public LbsSearchCriteria() {}

    public LbsSearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public LbsSearchCriteria(String key, String operation, Object[] values) {
        this.key = key;
        this.operation = operation;
        this.values = values;
    }

    public LbsSearchCriteria(String[] keys, String operation, Object value) {
        this.keys = keys;
        this.operation = operation;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getKeys() {
        return keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

}

