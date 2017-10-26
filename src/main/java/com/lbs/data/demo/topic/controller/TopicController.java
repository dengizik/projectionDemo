package com.lbs.data.demo.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(value="/topics", method= RequestMethod.GET)
    public List<Topic> getAllTopics(){

        return topicService.getAllTopics();
    }

    @RequestMapping(value = "/topicsSimple/{id}", method = RequestMethod.GET)
    public Page<TopicSimpleOuter> getAllSimpleTopics(@PathVariable String id){
        return topicService.getAllSimpleTopics(id);
    }

    @RequestMapping(value= "/topic/{id}", method = RequestMethod.GET)
    public Topic getTopic(@PathVariable String id){
        return topicService.getTopic(id);
    }

    @RequestMapping(value = "/topics", method = RequestMethod.POST)
    public void addTopic(@RequestBody Topic topic){//your request payload is going to contain a JSON representation of this topic instance.
        //with this line of code you say take the JSON object and convert it into Topic
        topicService.addTopic(topic);
    }

    @RequestMapping(value="/topics/{id}", method = RequestMethod.PUT)
    public void updateTopic(@RequestBody Topic topic, @PathVariable String id){
        topicService.updateTopic(id, topic);
    }

    @RequestMapping(value="/topics/{id}", method = RequestMethod.DELETE)
    public void deleteTopic(@PathVariable String id){
        topicService.deleteTopic(id);
    }
}
