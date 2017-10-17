package com.lbs.data.demo.toDo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public List<Course> getAllCourses(String topicId) {


        List<Course> topics = new ArrayList<>();
        courseRepository.findByTopicId(topicId)
                .forEach(topics::add);
        return topics;
    }


    public Course getCourse(String id){

       return courseRepository.findOne(id);
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Course topic) {

        courseRepository.save(topic);//repository finds the topic and updated if it exists, if not it inserts
    }

    public void deleteCourse(String id) {
//        topics.removeIf(t -> t.getId().equals(id));
        courseRepository.delete(id);
    }
}
