package com.lbs.data.demo.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic,String>,JpaSpecificationExecutorWithProjection<Topic> {//interface olması ne kadar ilginç
//public interface TopicRepository extends JpaRepository<Topic,String>, JpaSpecificationExecutor<Topic> {
    public static interface TopicSimple{
        String getId();
        String getName();
}

}
