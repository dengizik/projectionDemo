package com.lbs.data.demo;

import com.lbs.data.demo.courses.Course;
import com.lbs.data.demo.courses.CourseRepository;
import com.lbs.data.demo.courses.CourseSpec;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = DemoApplication.class)
public class DemoApplicationTests {

	@Autowired
	private CourseRepository courseRepository;

	private CourseSpec courseSpec;

	@Test
	public void contextLoads() {
	}

	@Test
	public void specificationWithProjection() {
		Specification<Course> where= Specifications.where(CourseSpec.idEq("Spring Kursu"));
		Page<CourseRepository.CourseWithoutParent> all = courseRepository.findAll(where,CourseRepository.CourseWithoutParent.class, null);
		Assertions.assertThat(all).isNotEmpty();
		System.out.println(all.getContent());
	}

//	@Test
//	public void findWithoutProjection(){
//		List<Course> byParentIsNull = courseRepository2.findByTopicIsNull();
//		Assertions.assertThat(byParentIsNull).isNotEmpty();
//
//	}
}


