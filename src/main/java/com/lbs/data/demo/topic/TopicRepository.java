package com.lbs.data.demo.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.stereotype.Repository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.List;


@Repository
//@RepositoryRestResource(excerptProjection = TopicSimpleOuter.class)
public interface TopicRepository extends JpaRepository<Topic,String>,JpaSpecificationExecutorWithProjection<Topic> {//interface olması ne kadar ilginç
//public interface TopicRepository extends JpaRepository<Topic,String>, JpaSpecificationExecutor<Topic> {

    //@Projection(name="topicSimple", types = Topic.class)
//    public interface TopicSimple{
//        String getId();
//        String getName();
//}

    List<TopicSimpleOuter> findById(String id);



}
