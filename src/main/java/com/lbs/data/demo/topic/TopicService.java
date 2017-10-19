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
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UberClass uberClass;


    private Topic topic;





    //işaretli entity (kendisini bu şekilde register edebilir)
    //ve controller'a simple hali gönderilebilir
    //gene repository'de simple halini tanımlamak zorunda
    //Ne yapmıyor? Context'de var olmuyor, gerek yok buna zaten Repo üzerinden erişilebiliyor
    //Repo'dan bağımsız context'te yer alamıyor çünkü bu simple interface'ler Repo'da olmak zorunda



    public List<Topic> getAllTopics() {


        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Page<TopicSimpleOuter> getAllSimpleTopics(String id){
        topic = new Topic();
        Class c = topic.getClass();
        Annotation an = c.getAnnotation(ProjectionWithSpecification.class);//get C's ProjectionWithSpec Annotation
        ProjectionWithSpecification projectionWithSpecification = (ProjectionWithSpecification) an;
        System.out.println(projectionWithSpecification.interfaceClass());
//        String projectionName = projectionWithSpecification.interfaceClass();

        Class<?> entityClass = null;
        //String projectionName = "com.lbs.data.demo.topic.TopicRepository$TopicSimple";
        String projectionName = "com.lbs.data.demo.topic.TopicSimpleOuter";
        try{
             entityClass = Class.forName(projectionName);
        }catch (ClassNotFoundException e){
            System.out.println("olmadı");
            System.out.println(e);
        }

        Specification<Topic> where = Specifications.where(TopicSpec.idEq(id));
		//Page<TopicRepository.TopicSimple> all = topicRepository.findAll(where,TopicRepository.TopicSimple.class, new PageRequest(0,10));
        Page<?> all =  topicRepository.findAll(where,entityClass.getClass(), new PageRequest(0,10));
        //Page<?> all = new PageImpl<>(topicRepository.findById(id));//bu çalışıyor
        return (Page <TopicSimpleOuter>) all;
    }


    public Topic getTopic(String id){

       return topicRepository.findOne(id);
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public void updateTopic(String id, Topic topic) {

        topicRepository.save(topic);//repository finds the topic and updated if it exists, if not it inserts
    }

    public void deleteTopic(String id) {
//        topics.removeIf(t -> t.getId().equals(id));
        topicRepository.delete(id);
    }
}
