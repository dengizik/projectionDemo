package com.lbs.data.demo.topic;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AnotherTopicSpec implements Specification<Topic> {

private Topic filter;

public AnotherTopicSpec(Topic filter) {
        super();
        this.filter = filter;
        }

public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> cq,
                             CriteriaBuilder cb) {

        Predicate p = cb.disjunction();

        if (filter.getId() != null) {
        p.getExpressions()
        .add(cb.equal(root.get("id"), filter.getId()));
        }
//
//        if (filter.getSurname() != null && filter.getAge() != null) {
//        p.getExpressions().add(
//        cb.and(cb.equal(root.get("surname"), filter.getSurname()),
//        cb.equal(root.get("age"), filter.getAge())));
//        }

        return p;

        }

        }