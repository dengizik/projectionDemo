package com.lbs.data.demo.topic.repository.spec;

//import com.lbs.config.multitenancy.postgres.LbsPostgresArrayPredicate;
//import org.apache.commons.lang3.ArrayUtils;
//import org.hibernate.jpa.criteria.OrderImpl;
//import org.springframework.data.jpa.domain.Specification;
//
//import javax.persistence.criteria.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Locale;
//
///**
// * Title :
// * Description :
// * Copyright : Copyright (c) 2016
// * Company : LBS
// * Created on 27.02.2017
// * @author Kemal.Eroglu
// * @version 1.0
// */
//
public class LbsFilterSpecification<T> {
//
//    private List<LbsSearchCriteria> searchCriteria;
//    private List<LbsQueryOrder> queryOrders;
//
//    public LbsFilterSpecification(LbsSearchCriteria searchCriteria) {
//        this(searchCriteria, null);
//    }
//
//    public LbsFilterSpecification(LbsSearchCriteria searchCriteria, LbsQueryOrder queryOrder) {
//        super();
//        this.searchCriteria = new ArrayList<>();
//        this.queryOrders = new ArrayList<>();
//        if(searchCriteria != null)
//            this.searchCriteria.add(searchCriteria);
//        if(queryOrder != null)
//            this.queryOrders.add(queryOrder);
//    }
//
//    public LbsFilterSpecification(List<LbsSearchCriteria> searchCriteria) {
//        this(searchCriteria, new ArrayList<>());
//    }
//
//    public LbsFilterSpecification(List<LbsSearchCriteria> searchCriteria, List<LbsQueryOrder> queryOrders) {
//        super();
//        this.searchCriteria = searchCriteria;
//        this.queryOrders = queryOrders;
//    }
//
//    @Override
//    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//        Predicate p = criteriaBuilder.and();
//        From<Object, Object> from = (From<Object, Object>) root;
//        for (LbsSearchCriteria criteria : searchCriteria) {
//
//            String key = criteria.getKey();
//            String[] keys = null;
//
//            if (key != null) {
//                String[] joinedKeys = key.split("\\.");
//                if (joinedKeys.length > 1) {
//                    // Handle second level relations
//                    Join<Object, Object> join = root.join(joinedKeys[0]);
//                    from = join;
//                    key = joinedKeys[1];
//                }
//            } else {
//                keys = criteria.getKeys();
//            }
//
//            // Handle Enum Values
//            if (key != null && from.get(key).getJavaType().isEnum()) {
//                if (criteria.getValue() != null && criteria.getValue() instanceof Double) {
//                    Object enumValue = from.get(key).getJavaType().getEnumConstants()[((Double)criteria.getValue()).intValue()];
//                    criteria.setValue(enumValue);
//                }
//                if (criteria.getValues() != null && criteria.getValues().length > 0) {
//                    Object[] enumValues = new Object[criteria.getValues().length];
//                    for (int i = 0; i < criteria.getValues().length; i++) {
//                        if (criteria.getValues()[i] != null) {
//                            if (criteria.getValues()[i] instanceof Double) {
//                                enumValues[i] = from.get(key).getJavaType().getEnumConstants()[((Double)criteria.getValues()[i]).intValue()];
//                            } else if (criteria.getValues()[i] instanceof Enum) {
//                                enumValues[i] = criteria.getValues()[i];
//                            }
//                        }
//                    }
//                    criteria.setValues(enumValues);
//                }
//            }
//
//            if (criteria.getOperation().equalsIgnoreCase(LbsOperatorConstants.EQUAL)) {
//                // Handle multiple keys for OR
//                if (key == null && keys != null) {
//                    List<Predicate> orPredicates = new ArrayList<>();
//                    Predicate equalsPredicate;
//                    for (String k : keys) {
//                        Expression<String> exp1 = criteriaBuilder.function("REPLACE", String.class,
//                                from.get(k), criteriaBuilder.literal("İ"), criteriaBuilder.literal("i"));
//                        Expression<String> exp2 = criteriaBuilder.function("REPLACE", String.class,
//                                exp1, criteriaBuilder.literal("I"), criteriaBuilder.literal("ı"));
//                        equalsPredicate = criteriaBuilder
//                                .equal(criteriaBuilder.lower(exp2), criteria.getValue().toString()
//                                        .toLowerCase(Locale.forLanguageTag("tr-TR")));
//                        orPredicates.add(equalsPredicate);
//                    }
//                    p.getExpressions().add(criteriaBuilder.or(orPredicates.toArray(new Predicate[orPredicates.size()])));
//                } else {
//                    if (criteria.getValue() == null) {
//                        p.getExpressions().add(criteriaBuilder.isNull(from.get(key)));
//                    } else {
//                        p.getExpressions().add(criteriaBuilder.equal(from.get(key), criteria.getValue()));
//                    }
//                }
//            } else if (key != null && criteria.getOperation().equalsIgnoreCase(LbsOperatorConstants.NOT_EQUAL)) {
//                p.getExpressions().add(criteriaBuilder.notEqual(from.get(key), criteria.getValue()));
//            } else if (key != null && criteria.getOperation().equalsIgnoreCase(LbsOperatorConstants.LESS_THAN)) {
//                p.getExpressions().add(criteriaBuilder.lessThan(from.get(key), (Double)criteria.getValue()));
//            } else if (key != null && criteria.getOperation().equalsIgnoreCase(LbsOperatorConstants.GREATER_THAN)) {
//                p.getExpressions().add(criteriaBuilder.greaterThan(from.get(key), (Double)criteria.getValue()));
//            } else if (key != null && criteria.getOperation().equalsIgnoreCase(LbsOperatorConstants.LESS_THAN_OR_EQUAL_TO)) {
//                p.getExpressions().add(criteriaBuilder.lessThanOrEqualTo(from.get(key), (Double)criteria.getValue()));
//            } else if (key != null && criteria.getOperation().equalsIgnoreCase(LbsOperatorConstants.GREATER_THAN_OR_EQUAL_TO)) {
//                p.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(from.get(key), (Double)criteria.getValue()));
//            } else if (criteria.getOperation().equalsIgnoreCase(LbsOperatorConstants.LIKE)) {
//                // Handle multiple keys for OR
//                if (key == null && keys != null) {
//                    List<Predicate> orPredicates = new ArrayList<>();
//                    Predicate likePredicate;
//                    for (String k : keys) {
//                        Expression<String> exp1 = criteriaBuilder.function("REPLACE", String.class,
//                                from.get(k), criteriaBuilder.literal("İ"), criteriaBuilder.literal("i"));
//                        Expression<String> exp2 = criteriaBuilder.function("REPLACE", String.class,
//                                exp1, criteriaBuilder.literal("I"), criteriaBuilder.literal("ı"));
//                        likePredicate = criteriaBuilder
//                                .like(criteriaBuilder.lower(exp2), ("%" + criteria.getValue().toString()
//                                        .toLowerCase(Locale.forLanguageTag("tr-TR")) + "%"));
//                        orPredicates.add(likePredicate);
//                    }
//                    p.getExpressions().add(criteriaBuilder.or(orPredicates.toArray(new Predicate[orPredicates.size()])));
//                } else {
//                    if (from.get(key).getJavaType() == String.class) {
//                        Expression<String> exp1 = criteriaBuilder
//                                .function("REPLACE", String.class, from.get(key), criteriaBuilder.literal("İ"), criteriaBuilder.literal("i"));
//                        Expression<String> exp2 = criteriaBuilder.function("REPLACE", String.class, exp1, criteriaBuilder.literal("I"), criteriaBuilder.literal("ı"));
//                        p.getExpressions().add(criteriaBuilder
//                                .like(criteriaBuilder.lower(exp2), ("%" + criteria.getValue().toString().toLowerCase(Locale.forLanguageTag("tr-TR")) + "%")));
//                    }
//                }
//            } else if (key != null && criteria.getOperation().equalsIgnoreCase(LbsOperatorConstants.STARTSWITH)) {
//                if (from.get(key).getJavaType() == String.class) {
//                    p.getExpressions().add(criteriaBuilder.like(criteriaBuilder.lower(from.get(key)), criteria.getValue().toString().toLowerCase() + "%"));
//                }
//            } else if (key != null && criteria.getOperation().equalsIgnoreCase(LbsOperatorConstants.IN)) {
//                if (from.get(key).getJavaType() == List.class) {    // Array in Array (for tags)
//                    if (ArrayUtils.isNotEmpty(criteria.getValues())) {
//                        Predicate arrayPredicate = new LbsPostgresArrayPredicate<String>(criteriaBuilder, Predicate.BooleanOperator.OR, from.get(key),
//                                Arrays.copyOf(criteria.getValues(), criteria.getValues().length, String[].class));
//                        p.getExpressions().add(arrayPredicate);
//                    } else {
//                        // criteriaBuilder.or() is always false, we "AND" the predicate with false to return empty result
//                        p.getExpressions().add(criteriaBuilder.or());
//                    }
//                } else {    // Object in Array
//                    if (ArrayUtils.isNotEmpty(criteria.getValues())) {
//                        final Path<Object> searchKey = from.get(key);
//                        p.getExpressions().add(searchKey.in(Arrays.asList(criteria.getValues())));
//                    } else {
//                        // criteriaBuilder.or() is always false, we "AND" the predicate with false to return empty result
//                        p.getExpressions().add(criteriaBuilder.or());
//                    }
//                }
//            }
//        }
//        handleQueryOrders(from, criteriaQuery, criteriaBuilder);
//
//        return p;
//    }
//
//    private void handleQueryOrders(From from, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder){
//        for (LbsQueryOrder queryOrder : queryOrders) {
//            CriteriaBuilder.Case cas = criteriaBuilder.selectCase();
//            for(LbsOrderCase ocase : queryOrder.getOrderCases()) {
//                boolean fieldOrValue = ocase.getOrderField() != null;
//                if (ocase.getOrderWhen() != null && ocase.getOrderWhen().getCaseField() != null) {
//                    if (ocase.getOrderWhen().getOperation().equalsIgnoreCase(LbsOperatorConstants.EQUAL)) {
//                        Predicate predicate = null;
//                        if (ocase.getOrderWhen().getCaseValue() == null)
//                            predicate = criteriaBuilder.isNull(from.get(ocase.getOrderWhen().getCaseField()));
//                        else
//                            predicate = criteriaBuilder.equal(from.get(ocase.getOrderWhen().getCaseField()), ocase.getOrderWhen().getCaseValue());
//                        addWhen(from, predicate, cas, ocase, fieldOrValue);
//                    }
//                    else if (ocase.getOrderWhen().getOperation().equalsIgnoreCase(LbsOperatorConstants.NOT_EQUAL)) {
//                        Predicate predicate = null;
//                        if (ocase.getOrderWhen().getCaseValue() == null)
//                            predicate = criteriaBuilder.isNotNull(from.get(ocase.getOrderWhen().getCaseField()));
//                        else
//                            predicate = criteriaBuilder.notEqual(from.get(ocase.getOrderWhen().getCaseField()), ocase.getOrderWhen().getCaseValue());
//                        addWhen(from, predicate, cas, ocase, fieldOrValue);
//                    }
//                    else if (ocase.getOrderWhen().getOperation().equalsIgnoreCase(LbsOperatorConstants.LESS_THAN)) {
//                        Predicate predicate = criteriaBuilder.lessThan(from.get(ocase.getOrderWhen().getCaseField()), (String)ocase.getOrderWhen().getCaseValue());
//                        addWhen(from, predicate, cas, ocase, fieldOrValue);
//                    }
//                    else if (ocase.getOrderWhen().getOperation().equalsIgnoreCase(LbsOperatorConstants.GREATER_THAN)) {
//                        Predicate predicate = criteriaBuilder.greaterThan(from.get(ocase.getOrderWhen().getCaseField()), (String)ocase.getOrderWhen().getCaseValue());
//                        addWhen(from, predicate, cas, ocase, fieldOrValue);
//                    }
//                    else if (ocase.getOrderWhen().getOperation().equalsIgnoreCase(LbsOperatorConstants.LIKE)) {
//                        Predicate predicate = criteriaBuilder.like(from.get(ocase.getOrderWhen().getCaseField()), (String)ocase.getOrderWhen().getCaseValue());
//                        addWhen(from, predicate, cas, ocase, fieldOrValue);
//                    }
//                }
//                else {
//                    if (fieldOrValue)
//                        cas.otherwise(from.get(ocase.getOrderField()));
//                    else
//                        cas.otherwise(ocase.getOrderVaLue());
//                }
//            }
//            OrderImpl order = new OrderImpl(cas, queryOrder.getOrderDirection().isAscending() ? true : false);
//            criteriaQuery.orderBy(order);
//        }
//    }
//
//    private void addWhen(From from, Predicate predicate, CriteriaBuilder.Case cas, LbsOrderCase ocase, boolean fieldOrValue){
//        if (fieldOrValue)
//            cas.when(predicate, from.get(ocase.getOrderField()));
//        else
//            cas.when(predicate, ocase.getOrderVaLue());
//    }
}