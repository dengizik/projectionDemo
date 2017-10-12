package com.lbs.data.demo;

import com.lbs.data.demo.courses.Course;
import com.lbs.data.demo.courses.CourseRepository;
import com.lbs.data.demo.courses.CourseRepository2;
import com.lbs.data.demo.courses.CourseSpec;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = DemoApplication.class)
public class DemoApplicationTests {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseRepository2 courseRepository2;


	private CourseSpec courseSpec;

	@Test
	public void contextLoads() {
	}

	@Test
	public void specificationWithProjection() {

		Page<CourseRepository.CourseWithoutParent> all = courseRepository.findAll(courseSpec,CourseRepository.CourseWithoutParent.class, null);
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
