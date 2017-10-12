package com.lbs.data.demo.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.List;
//deneme
//public interface CourseRepository extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {//interface olması ne kadar ilginç
    public interface CourseRepository extends JpaRepository<Course, String>, JpaSpecificationExecutorWithProjection<Course> {//interface olması ne kadar ilginç

    public List<CourseWithoutParent> findByTopicIsNull();

    public List<Course> findByTopicId(String topicId);

    public static interface CourseWithoutParent{
        String getId();
        String getName();
//        List<CourseWithoutParent> getChild();
    }
}
