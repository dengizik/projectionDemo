package com.lbs.data.demo.topic.repository.spec;

import com.lbs.data.demo.topic.Topic_;
import com.lbs.data.demo.topic.model.entity.Topic;
import org.hibernate.criterion.CriteriaQuery;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.Metamodel;

public class TopicSpec {
    public static Specification<Topic> idEq(String id){
       
        return (root, query, cb) -> cb.equal(root.get(Topic_.id),id);

    }
}
