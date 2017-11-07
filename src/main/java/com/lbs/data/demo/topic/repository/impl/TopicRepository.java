package com.lbs.data.demo.topic.repository.impl;

import com.lbs.data.demo.topic.model.entity.ProjectorInterfaces.TopicProjector;
import com.lbs.data.demo.topic.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.List;


@Repository
public interface TopicRepository extends JpaRepository<Topic,String>,JpaSpecificationExecutorWithProjection<Topic> {
    List<TopicProjector> findById(String id);
}
