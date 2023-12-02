package com.demo.cruddemo.dao;

import com.demo.cruddemo.entity.Course;
import com.demo.cruddemo.entity.Instructor;
import com.demo.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface AppDao {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteinstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int id);
    Instructor findInstructorByIdJoinFetch(int id);
}
