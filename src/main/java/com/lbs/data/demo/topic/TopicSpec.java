package com.lbs.data.demo.topic;

import org.springframework.data.jpa.domain.Specification;

public class TopicSpec {
    public static Specification<Topic> idEq(String id){
        return (root, query, cb) -> cb.equal(root.get(Topic_.id),id);
    }
}
