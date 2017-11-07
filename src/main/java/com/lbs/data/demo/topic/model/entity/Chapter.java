package com.lbs.data.demo.topic.model.entity;

import com.lbs.data.demo.topic.model.ProjectionWithSpecification;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@ProjectionWithSpecification(projector = "com.lbs.data.demo.topic.model.entity.ProjectorInterfaces.ChapterProjector")
public class Chapter {
    @Id
    @Column(name = "ID")
    private String id;
    private String name;
    private String title;
    private String description;
}
