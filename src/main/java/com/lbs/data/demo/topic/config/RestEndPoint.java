package com.lbs.data.demo.topic.config;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RestEndPoint { //TODO: projection'ı koy, RestEndPoint
//    .yml'in içinde projection olmayacak yml'in içindekilerle projektion'ın içindekiler çakışabilir
//    List of RestEndPoint'in içinde entity'ler var,
//uygulama ayağa kalkarken Endpoint'lerin içindeki entity'lere bakacak diyecek ki bunun projekjion'ı bu (annotation yazdık ya üstüne)
//1'den fazla projection olursa ne olacak?
    //örtüşmeyince ne olacak?

    private String autoCompleteId;
    private String entityName;
    private ArrayList<String> listFields;
    private ArrayList<String> keyFields;
    private ArrayList<String> filterFields;
    private String projector;

    public String getAutoCompleteId() {
        return autoCompleteId;
    }

    public void setAutoCompleteId(String autoCompleteId) {
        this.autoCompleteId = autoCompleteId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public ArrayList<String> getListFields() {
        return listFields;
    }

    public void setListFields(ArrayList<String> listFields) {
        this.listFields = listFields;
    }

    public ArrayList<String> getKeyFields() {
        return keyFields;
    }

    public void setKeyFields(ArrayList<String> keyFields) {
        this.keyFields = keyFields;
    }

    public ArrayList<String> getFilterFields() {
        return filterFields;
    }

    public void setFilterFields(ArrayList<String> filterFields) {
        this.filterFields = filterFields;
    }

    public String getProjector() {
        return projector;
    }

    public void setProjector(String projector) {
        this.projector = projector;
    }
}