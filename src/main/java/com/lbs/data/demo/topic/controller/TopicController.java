package com.lbs.data.demo.topic.controller;

import com.lbs.base.config.logger.LbsLogger;
import com.lbs.data.demo.topic.config.RestEndPoint;
import com.lbs.data.demo.topic.config.RestEndPointConfig;
import com.lbs.data.demo.topic.model.entity.ProjectorInterfaces.TopicProjector;
import com.lbs.data.demo.topic.model.entity.Topic;
import com.lbs.data.demo.topic.service.TopicService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lbs.data.demo.topic.controller.endpoint.TopicUtilityServiceEndPoint.*;


@RestController
public class TopicController {//TODO: basecontroller

    @Autowired
    private TopicService topicService;

    private final Logger logger = LbsLogger.getLogger(TopicController.class);


    private Map<String, RestEndPoint> restEndPointMap;

    @Autowired
    private RestEndPointConfig restEndPointConfig; //TODO: autowire yapmıyoruz çünkü bean değil. ama soho'da bean olmamasına rağmen autowired

    @PostConstruct
    private void prepareRestEndpointMap() {
        restEndPointMap = new HashMap<>();
        List<RestEndPoint> restEndPointList = restEndPointConfig.getRestEndPoints();
        for (RestEndPoint restEndPoint : restEndPointList) {
            restEndPointMap.put(restEndPoint.getAutoCompleteId(), restEndPoint);
        }
    }

    @RequestMapping(value = "/{autoCompleteId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<TopicProjector> search(
            @PathVariable String autoCompleteId,
            @RequestParam(value = "searchKey", required = false) String searchKey,
            @RequestParam(value = "searchValue", required = false) String searchValue,
            @RequestParam(value = "filterKey", required = false) String filterKey,
            @RequestParam(value = "filterValue", required = false) String filterValue,
            @RequestParam(value = "searchCriteriaJson", required = false) String searchCriteriaJson,
            @RequestParam(value = "maxResults", required = false) Integer maxResults) {

        RestEndPoint restEndPoint = restEndPointMap.get(autoCompleteId);//hangi restendpoint'e denk geldiğini bul //entityname

        //logger.info("REST request to search");
        Page<TopicProjector> searchResult = null;

        try{
            searchResult = topicService.search(restEndPoint.getEntityName(), restEndPoint.getListFields(), searchCriteriaJson, maxResults, restEndPoint.getKeyFields());
            //String entityName, ArrayList<String> listFieldList, String searchCriteriaJson, Integer maxResults, ArrayList<String> keyFields
//            public Page<TopicProjector> search(String entityName, ArrayList<String> listFieldList, String searchCriteriaJson, Integer maxResults, ArrayList<String> keyFields)
        }catch(Exception e){
            System.out.println(e);
        }

        return searchResult;
    }


    @RequestMapping(value=TOPICS, method= RequestMethod.GET)
    public List<Topic> getAllTopics(){
        return topicService.getAllTopics();
    }

    @RequestMapping(value = SIMPLE_TOPICS, method = RequestMethod.GET)
    public Page<TopicProjector> getAllSimpleTopics(@PathVariable (value=TOPIC_ID) String id){
        return topicService.getAllSimpleTopics(id);
    }

    @RequestMapping(value= GET_A_TOPIC, method = RequestMethod.GET)
    public Topic getTopic(@PathVariable (value=TOPIC_ID) String id){
        return topicService.getTopic(id);
    }

    @RequestMapping(value = ADD_TOPIC, method = RequestMethod.POST)
    public List<Topic> addTopic(@RequestBody Topic topic){//your request payload is going to contain a JSON representation of this topic instance.
        //with this line of code you say take the JSON object and convert it into Topic
        List<Topic> topics = new ArrayList<>();
        topicService.addTopic(topic).forEach(topics::add);
        return topics;

    }

    @RequestMapping(value=UPDATE_TOPIC, method = RequestMethod.PUT)
    public List<Topic> updateTopic(@RequestBody Topic topic, @PathVariable (value=TOPIC_ID) String id){
        List<Topic> topics = new ArrayList<>();
        topicService.updateTopic(id, topic).forEach(topics::add);
        return topics;
    }

    @RequestMapping(value=DELETE_TOPIC, method = RequestMethod.DELETE)
    public List<Topic> deleteTopic(@PathVariable (value=TOPIC_ID) String id){
        List<Topic> topics = new ArrayList<>();
        topicService.deleteTopic(id).forEach(topics::add);
        return topics;
    }
}
