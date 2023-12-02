package com.demo.cruddemo;

import com.demo.cruddemo.dao.AppDao;
import com.demo.cruddemo.entity.Course;
import com.demo.cruddemo.entity.Instructor;
import com.demo.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao) {
		return runner -> {
			//createInstructorWithCourse(appDao);
			//findInstructorWithCourse(appDao);
			//findCoursesForInstructor(appDao);
			findInstructorWithCourseJoinFetch(appDao);
		};

	}

	private void findInstructorWithCourseJoinFetch(AppDao appDao) {

        int id=3;
		System.out.println("Finding instructor id:"+id);
		Instructor tempInstructor=appDao.findInstructorByIdJoinFetch(id);
		System.out.println("tempInstructor:"+tempInstructor);
		System.out.println("The associated courses:" + tempInstructor.getCourses());
		System.out.println("Its done");

	}

	private void findCoursesForInstructor(AppDao appDao) {
		int id=4;
		System.out.println("Finding instructor id:"+id);
		Instructor tempInstructor=appDao.findInstructorById(id);
		System.out.println("tempInstructor" +tempInstructor);
		System.out.println("Finding courses for instructor id:"+id);
		List<Course> courses=appDao.findCoursesByInstructorId(id);
		tempInstructor.setCourses(courses);
		System.out.println("The Associated courses:"+tempInstructor.getCourses());

	}

	private void findInstructorWithCourse(AppDao appDao) {

		int id=1;
		System.out.println("Finding instructor id:"+id);
		Instructor tempInstructor=appDao.findInstructorById(id);
		System.out.println("tempInstructor:"+tempInstructor);
		System.out.println("The associated courses:"+tempInstructor.getCourses());

	}

	private void createInstructorWithCourse(AppDao appDao) {

		Instructor tempInstructor=new Instructor("Mahendra","Dhoni","mahendrasingh@123");
		InstructorDetail tempInstructorDetail=new InstructorDetail("http://www.mahendrasingh.com","Playing");
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		Course tempCourse1=new Course("Cricket-Love to learn");
		Course tempCourse2=new Course("Football-Focus to play");

		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		System.out.println("Saving instructor:" +tempInstructor);
		System.out.println("Courses:" + tempInstructor.getCourses());
		appDao.save(tempInstructor);
		System.out.println("Done...");

	}
}