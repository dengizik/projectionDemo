package com.lbs.data.demo.topic.model.entity;

//import javax.persistence.metamodel.Metamodel;

//import com.lbs.config.multitenancy.postgres.LbsPostgresArrayPredicate;
import com.lbs.data.demo.topic.repository.spec.LbsSearchCriteria;
import com.lbs.data.demo.topic.util.LbsQueryOrder;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class TopicSpec implements Specification<Topic> {
    //TODO: make list array
    private LbsSearchCriteria searchCriteria;
    private List<LbsQueryOrder> queryOrders;

//TODO: custom specification
    public TopicSpec(LbsSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public <E> TopicSpec(LbsSearchCriteria searchCriteria, List<LbsQueryOrder> queryOrders) {
        super();
        this.searchCriteria = searchCriteria;
        this.queryOrders = queryOrders;
    }

    public static Specification<Topic> idEq(String id){

        return (root, query, cb) -> cb.equal(root.get("id"),id);
       // return (root, query, cb) -> cb.equal(root.get(topic),id);
    }



    @Override
    public Predicate toPredicate
            (Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        //TODO: deep dive into SOHO code here
        //for (LbsSearchCriteria aCriteria:searchCriteria){
            if (searchCriteria.getOperation().equalsIgnoreCase(">")) {
                return criteriaBuilder.greaterThanOrEqualTo(
                        root.<String> get(searchCriteria.getKey()), searchCriteria.getValue().toString());
            }
            else if (searchCriteria.getOperation().equalsIgnoreCase("<")) {
                return criteriaBuilder.lessThanOrEqualTo(
                        root.<String> get(searchCriteria.getKey()), searchCriteria.getValue().toString());
            }
            else if (searchCriteria.getOperation().equalsIgnoreCase(":")) {
                if (root.get(searchCriteria.getKey()).getJavaType() == String.class) {
                    return criteriaBuilder.like(
                            root.<String>get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
                } else {
                    return criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
                }
            }
        //}

        return null;
    }


}
