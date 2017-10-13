package com.lbs.data.demo;

import com.lbs.data.demo.courses.Course;
import com.lbs.data.demo.courses.CourseRepository;
import com.lbs.data.demo.courses.CourseSpec;
import com.lbs.data.demo.topic.AnotherTopicSpec;
import com.lbs.data.demo.topic.Topic;
import com.lbs.data.demo.topic.TopicRepository;
import com.lbs.data.demo.topic.TopicSpec;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = DemoApplication.class)
public class DemoApplicationTests {

//	@Autowired
//	private CourseRepository courseRepository;


	@Autowired
	private TopicRepository topicRepository;

	@Test
	public void contextLoads() {
	}

//	@Test
//	public void specificationWithProjection() {
//		Specification<Course> where= Specifications.where(CourseSpec.idEq("JSKursu"));
//		Page<CourseRepository.CourseWithoutParent> all = courseRepository.findAll(where,CourseRepository.CourseWithoutParent.class, null);
//		Assertions.assertThat(all).isNotEmpty();
//		System.out.println(all.getContent());
//	}


	@Test
	public void specificationWithProjection() {
//		Specification<Topic> where= Specifications.where(TopicSpec.idEq("Java"));
//		Page<TopicRepository.TopicSimple> all = topicRepository.findAll(where,TopicRepository.TopicSimple.class, null);
//		Assertions.assertThat(all).isNotEmpty();
//		System.out.println(all.getContent());


		Topic filter = new Topic();
		filter.setId("Bir");

		Specification<Topic> topicSpecification = new AnotherTopicSpec(filter);

		List<Topic> all = topicRepository.findAll(topicSpecification);
		Assertions.assertThat(all).isNotEmpty();


	}

}


