package com.lbs.data.demo.topic.repository.impl;

import com.lbs.data.demo.topic.TopicSimpleOuter;
import com.lbs.data.demo.topic.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.List;


@Repository
public interface TopicDAO extends JpaRepository<Topic,String>,JpaSpecificationExecutorWithProjection<Topic> {
    List<TopicSimpleOuter> findById(String id);
}
