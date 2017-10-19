package com.lbs.data.demo.topic;

import org.springframework.data.rest.core.config.Projection;

//@Projection(name="topicSimple", types = Topic.class)
public interface TopicSimpleOuter {
        String getId();
        String getName();
}
