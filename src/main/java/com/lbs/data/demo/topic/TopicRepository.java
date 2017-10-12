package com.lbs.data.demo.topic;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, String> {//interface olması ne kadar ilginç
    //crudrepository is a generic type
    //all common methods below have type information
    //<Entity type, id type>

    //getAllTopics()
    //getTopic(String id)
    //updateTopic(Topic t)
    //deleteTopic(String id)
    //instead of these use crud repo

}
