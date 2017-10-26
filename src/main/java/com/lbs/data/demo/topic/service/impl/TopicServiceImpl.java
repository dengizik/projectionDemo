package com.lbs.data.demo.topic;

import com.lbs.data.demo.UberClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDAO topicDAO;

    @Autowired
    private UberClass uberClass;


    private Topic topic; //TODO: @autowired


    //işaretli entity (kendisini bu şekilde register edebilir)
    //ve controller'a simple hali gönderilebilir
    //gene repository'de simple halini tanımlamak zorunda
    //Ne yapmıyor? Context'de var olmuyor, gerek yok buna zaten Repo üzerinden erişilebiliyor
    //Repo'dan bağımsız context'te yer alamıyor çünkü bu simple interface'ler Repo'da olmak zorunda



    public List<Topic> getAllTopics() {


        List<Topic> topics = new ArrayList<>();
        topicDAO.findAll().forEach(topics::add);
        return topics;
    }

    public Page<TopicSimpleOuter> getAllSimpleTopics(String id){
        topic = new Topic();
        Class c = topic.getClass();
        Annotation an = c.getAnnotation(ProjectionWithSpecification.class);//get C's ProjectionWithSpec Annotation
        ProjectionWithSpecification projectionWithSpecification = (ProjectionWithSpecification) an;
        System.out.println(projectionWithSpecification.projector());
        Class<?> entityClass = null;
        try{
             entityClass = Class.forName(projectionWithSpecification.projector());
        }catch (ClassNotFoundException e){
            System.out.println("customException");
            System.out.println(e);
        }catch (Exception e){
            e.printStackTrace();
        }

        Specification<Topic> where = Specifications.where(TopicSpec.idEq(id));
       //java.lang.IllegalArgumentException: Projection type must be an interface” Error
        Page<?> all = null;
        try{
            all =  topicDAO.findAll(where,entityClass, new PageRequest(0,10));
        }catch(IllegalArgumentException e){
            System.out.println("another custom exception");
        }catch (Exception e){
            e.printStackTrace();
        }

        //Page<?> all = new PageImpl<>(topicRepository.findById(id));//bu çalışıyor
        if (all != null){
            return (Page <TopicSimpleOuter>) all;
        }
        return (Page <TopicSimpleOuter>) all; //TODO: burayı nasıl döndürsem?

    }


    public Topic getTopic(String id){

       return topicDAO.findOne(id);
    }

    public void addTopic(Topic topic) {
        topicDAO.save(topic);
    }

    public void updateTopic(String id, Topic topic) {

        topicDAO.save(topic);//repository finds the topic and updated if it exists, if not it inserts
    }

    public void deleteTopic(String id) {
//        topics.removeIf(t -> t.getId().equals(id));
        topicDAO.delete(id);
    }
}