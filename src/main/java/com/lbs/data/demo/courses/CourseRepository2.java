package com.lbs.data.demo.courses;

import org.springframework.data.jpa.repository.JpaRepository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.List;

//public interface CourseRepository extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {//interface olması ne kadar ilginç
    public interface CourseRepository2 extends JpaRepository<Course, String> {//interface olması ne kadar ilginç

    public List<Course> findByTopicIsNull();

}
