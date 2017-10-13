package com.lbs.data.demo.customer;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AnotherCustomerSpec implements Specification<Customer> {

private Customer filter;

public AnotherCustomerSpec(Customer filter) {
        super();
        this.filter = filter;
        }

public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> cq,
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