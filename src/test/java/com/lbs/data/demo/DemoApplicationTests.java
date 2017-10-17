package com.lbs.data.demo;

import com.lbs.data.demo.topic.AnotherTopicSpec;
import com.lbs.data.demo.topic.Topic;
import com.lbs.data.demo.topic.TopicRepository;
import com.lbs.data.demo.topic.TopicSpec;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = DemoApplication.class)
public class DemoApplicationTests {

//	@Autowired
//	private CourseRepository courseRepository;


	@Autowired
	private TopicRepository topicRepository;

	@Before
    public void init() {

		Topic topic = new Topic();
		topic.setId("İki");
		topic.setName("Hello");
		topicRepository.save(topic); }



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
//		Specification<Topic> where = Specifications.where(TopicSpec.idEq("İki"));
//		Page<TopicRepository.TopicSimple> all = topicRepository.findAll(where,TopicRepository.TopicSimple.class, new PageRequest(0,10));
//		Iterator<TopicRepository.TopicSimple> anIterator = all.iterator();
//		while (anIterator.hasNext()){
//			System.out.println("anIterator.next().getId(): " +anIterator.next().getId());
//			System.out.println("anIterator.next().getName()" + anIterator.next().getName());
//		}

		List<TopicRepository.TopicSimple> result = topicRepository.findById("İki");
		while(result.iterator().hasNext()){
			System.out.println("result.iterator().next().getName(): " + result.iterator().next().getName());
			System.out.println("result.iterator().next().getId(): " + result.iterator().next().getId());
		}
	}
}


