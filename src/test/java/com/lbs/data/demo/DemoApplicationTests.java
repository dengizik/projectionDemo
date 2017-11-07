package com.lbs.data.demo;

import com.lbs.data.demo.topic.model.entity.ProjectorInterfaces.TopicProjector;
import com.lbs.data.demo.topic.model.entity.Topic;
import com.lbs.data.demo.topic.model.entity.TopicSpec;
import com.lbs.data.demo.topic.repository.impl.TopicRepository;
import com.lbs.data.demo.topic.repository.spec.LbsSearchCriteria;
import com.lbs.data.demo.topic.service.TopicService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = DemoApplication.class)
public class DemoApplicationTests {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private TopicService topicService;


	@Before
    public void init() {

		Topic topic = new Topic();
		topic.setId("Bir");
		topic.setName("Java Topic");
		topic.setDescription("A long description");
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
////		Specification<Topic> where = Specifications.where(TopicSpec.idEq("İki"));
//		List<LbsSearchCriteria> searchCriteria = new ArrayList<LbsSearchCriteria>();
//		searchCriteria.add(new LbsSearchCriteria("name", ":", "Java Topic"));
//		TopicSpec spec =
//				new TopicSpec(searchCriteria);
//		Page<TopicProjector> all = topicRepository.findAll(spec,TopicProjector.class, new PageRequest(0,10));

		//List<TopicProjector> result = topicRepository.findById("Bir");

//		Assertions.assertThat(all).isNotEmpty();
	}



	@Test
	public void givenLast_whenGettingListOfUsers_thenCorrect() {
//		List<LbsSearchCriteria> searchCriteria = new ArrayList<LbsSearchCriteria>();
		LbsSearchCriteria searchCriteria = new LbsSearchCriteria("name", ":", "Java Topic");
		//searchCriteria.add(new LbsSearchCriteria("name", ":", "Java Topic"));
		TopicSpec spec =
				new TopicSpec(searchCriteria);

		Page<TopicProjector> allResult = null;
//		allResult = (Page<TopicProjector>) topicRepository.findAll(spec, TopicProjector.class,new PageRequest(0,10) );
		allResult = (Page<TopicProjector>) topicService.getAllSimpleTopics("Bir");

		Assertions.assertThat(allResult).isNotEmpty();
	}
}


