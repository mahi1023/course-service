package com.demo.course.course_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.course.course_service.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
