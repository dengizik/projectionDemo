package com.lbs.data.demo.courses;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CourseSpec implements Specification<Course> {

    private Course filter;

    public CourseSpec (Course filter) {
        super();
        this.filter = filter;
    }
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p = criteriaBuilder.disjunction();

                if(filter.getId() != null) {
                        p.getExpressions().add(criteriaBuilder.equal(root.get(("id")),filter.getId()));
                }
            return p;
            }
        }


