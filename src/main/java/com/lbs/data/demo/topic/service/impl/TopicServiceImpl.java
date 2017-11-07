package com.lbs.data.demo.topic.service.impl;

import com.lbs.base.config.logger.LbsLogger;
import com.lbs.data.demo.topic.config.ApplicationConfiguration;
import com.lbs.data.demo.topic.config.ContextProvider;
import gherkin.deps.com.google.gson.reflect.TypeToken;
import com.lbs.data.demo.topic.exception.ResourceNotFoundException;
import com.lbs.data.demo.topic.model.ProjectionWithSpecification;
import com.lbs.data.demo.topic.model.entity.ProjectorInterfaces.TopicProjector;
import com.lbs.data.demo.topic.model.entity.Topic;
import com.lbs.data.demo.topic.repository.impl.TopicRepository;
import com.lbs.data.demo.topic.model.entity.TopicSpec;
import com.lbs.data.demo.topic.repository.spec.LbsSearchCriteria;
import com.lbs.data.demo.topic.service.TopicService;
import com.lbs.data.demo.topic.util.GsonUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.lang.annotation.Annotation;
import java.util.*;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

//    @Autowired
//    private WebApplicationContext appContext;

    private final Logger logger = LbsLogger.getLogger(TopicServiceImpl.class);
    private Topic topic;
    static HashMap<String, String> hashMap = new HashMap<>();

//    @Autowired
//    AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext;

    @Autowired
    ApplicationContext context;

    public List<Topic> getAllTopics() {
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Page<TopicProjector> getAllSimpleTopics(String id){
        HashMap<String, String> annotatedClasses = new HashMap<String, String>();
        try{
            hashMap = (HashMap<String, String>) context.getBean("annotatedClass");
        }catch(Exception e){
            logger.error(e.getLocalizedMessage());
        }

        Class c = Topic.class;
        Annotation an = c.getAnnotation(ProjectionWithSpecification.class);//get C's ProjectionWithSpec Annotation
        ProjectionWithSpecification projectionWithSpecification = (ProjectionWithSpecification) an;


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
//            all =  new PageImpl<TopicProjector>(topicDAO.findById(id));
            all =  topicRepository.findAll(where,entityClass, new PageRequest(0,10));
        }catch(IllegalArgumentException e){
            System.out.println("another custom exception");
        }catch (Exception e){
            e.printStackTrace();
        }
        if (all != null){
            return (Page <TopicProjector>) all;
        }
        return (Page <TopicProjector>) all; //TODO:

    }


    public Topic getTopic(String id){

       return topicRepository.findOne(id);
    }

    public List<Topic> addTopic(Topic topic) {
        if (topic == null) {
            throw new ResourceNotFoundException(" ", "user not found");
        }
        Topic toBeAddedTopic = topicRepository.findOne(topic.getId());

        if (toBeAddedTopic != null) {
            throw new ResourceNotFoundException(toBeAddedTopic.getId().trim(), "user already exists");
        }
        topicRepository.save(topic);
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    } //repository finds the topic and updated if it exists, if not it inserts

    public List<Topic> updateTopic(String id, Topic topic) {//TODO: id not necessary, use topic.getid
            Topic searchedTopic = topicRepository.findOne(id);

            if (searchedTopic == null) {
                throw new ResourceNotFoundException(id.trim(), "user not found");
            }
            topicRepository.save(topic);//repository finds the topic, checks if request body exists and updated if it exists, if not it inserts
            List<Topic> topics = new ArrayList<>();
            topicRepository.findAll().forEach(topics::add);
            return topics;
    }

    public List<Topic> deleteTopic(String id) {
        Topic searchedTopic = topicRepository.findOne(id);

        if (searchedTopic == null) {
            throw new ResourceNotFoundException(id.trim(), "user not found");
        }

        topicRepository.delete(id);
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }
    public Page<TopicProjector> search(String entityName, ArrayList<String> listFieldList, String searchCriteriaJson, Integer maxResults, ArrayList<String> keyFields) throws ClassNotFoundException, IllegalAccessException {
//TODO:ilk olarak bunu concrete olarak implemente et, sonra objeye çevir

        Page<TopicProjector> all = null;
        try {
            //List<LbsSearchCriteria> searchCriteria = GsonUtil.getGson().fromJson(searchCriteriaJson, new TypeToken<List<LbsSearchCriteria>>() {//TODO: convert to list
            LbsSearchCriteria searchCriteria = GsonUtil.getGson().fromJson(searchCriteriaJson, new TypeToken<LbsSearchCriteria>(){}.getType());//aslında bu da bir reflection

            TopicSpec topicSpec = new TopicSpec(searchCriteria);

            //TODO: Reflection kullanmadan bunu yap
//            Repositories repositories = new Repositories(appContext);
//            String className = "com.lbs.soho.domain.soho." + entityName;
//            Class<?> entityClass = Class.forName(className);
//
//            ILbsPagingSortingAndSpecRepository repository = (ILbsPagingSortingAndSpecRepository) repositories.getRepositoryFor(entityClass);

            //TODO: şimdilik concrete class kullandım
            if (maxResults != null) {
//                Pageable pageable = getPageable(null, maxResults, keyField);
//                resultList = topicRepository.findAll(spec, pageable).getContent();
                all = topicRepository.findAll(topicSpec, TopicProjector.class,new PageRequest(0,10) );
                return all;
            } else {
                all = null;
            }
        } catch (Throwable e) {//TODO: Logo logs
//            logger.error("Search" + entityName + " - " + searchCriteriaJson, e);
            System.out.println(e);
        }
        return (Page <TopicProjector>)all;
    }
}
