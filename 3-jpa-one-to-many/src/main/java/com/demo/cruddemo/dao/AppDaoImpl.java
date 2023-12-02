package com.demo.cruddemo.dao;

import com.demo.cruddemo.entity.Course;
import com.demo.cruddemo.entity.Instructor;
import com.demo.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao{

    private EntityManager entityManager;
    @Autowired
    public AppDaoImpl(EntityManager entityManager)
    {
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor instructor) {

        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return  entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor tempInstructor=entityManager.find(Instructor.class,id);
         entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    @Transactional
    public void deleteinstructorDetailById(int id) {
        InstructorDetail tempInstructorDetail=entityManager.find(InstructorDetail.class,id);

        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(tempInstructorDetail);

    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

       TypedQuery<Course> query=entityManager.createQuery
               ("from Course where instructor.id=:data",Course.class);
       query.setParameter("data",id);
       List<Course> courses=query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {

        TypedQuery<Instructor> query=entityManager.createQuery(
                "select i from Instructor i JOIN FETCH i.courses where i.id = :data", Instructor.class);
        query.setParameter("data",id);
        Instructor instructor=query.getSingleResult();
        return instructor;

    }
}
