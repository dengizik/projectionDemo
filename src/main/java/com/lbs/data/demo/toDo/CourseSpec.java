package com.lbs.data.demo.toDo;

import com.lbs.data.demo.courses.Course_;
import org.springframework.data.jpa.domain.Specification;


public class CourseSpec {
    public static Specification<Course> idEq(String id){
        return (root, query, cb) -> cb.equal(root.get(Course_.id),id);
    }
}



