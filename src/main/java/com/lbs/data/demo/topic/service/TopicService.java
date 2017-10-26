package com.lbs.data.demo.topic;

import org.springframework.data.domain.Page;

import java.util.List;

public interface TopicService {
    public List<Topic> getAllTopics() ;

    public Page<TopicSimpleOuter> getAllSimpleTopics(String id);

    public Topic getTopic(String id);

    public void addTopic(Topic topic);

    public void updateTopic(String id, Topic topic) ;

    public void deleteTopic(String id);
}
