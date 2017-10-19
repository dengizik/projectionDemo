package com.lbs.data.demo.topic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@ProjectionWithSpecification (interfaceClass = "com.lbs.data.demo.topic.TopicSimpleOuter")
public class Topic {

    @Id
    @Column(name = "ID")
    //ya da bu field'leri işaretle (bu daha zor)
    private String id;
    private String name;
    private String description;

    public Topic() {

    }

    public Topic(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
