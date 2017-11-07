package com.lbs.data.demo.topic.service;

import com.lbs.data.demo.topic.model.entity.Topic;
import com.lbs.data.demo.topic.model.entity.ProjectorInterfaces.TopicProjector;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface TopicService {
    public List<Topic> getAllTopics() ;

    public Page<TopicProjector> getAllSimpleTopics(String id);

    public Topic getTopic(String id);

    public List<Topic> addTopic(Topic topic);

    public List<Topic> updateTopic(String id, Topic topic) ;

    public List<Topic> deleteTopic(String id);

//    public Page<TopicProjector> search(String entityName, ArrayList<String> listFieldList, String searchKey, String searchOperation, String searchValue, String filterKey, String filterValue, Integer maxResults) throws ClassNotFoundException, IllegalAccessException;
    public Page<TopicProjector> search(String entityName, ArrayList<String> listFieldList, String searchCriteriaJson, Integer maxResults, ArrayList<String> keyFields) throws ClassNotFoundException, IllegalAccessException;
}
