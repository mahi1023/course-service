package com.demo.course.course_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.course.course_service.entity.Course;
import com.demo.course.course_service.producer.KafkaProducer;
import com.demo.course.course_service.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	  @Autowired
	    private CourseService courseService;
	    
	  @Autowired
	    private KafkaProducer kafkaProducer;
	  
	  @PostMapping
	    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
		  try {
	        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
		  }
		  catch(Exception e) {
			  System.out.print(e);
		      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		  }
	    }

	  @GetMapping("/{id}")
	    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
	        Optional<Course> course = courseService.getCourseById(id);
	        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }
	  
	  
	    @PostMapping("/enroll")
	    public ResponseEntity<String> enrollStudent(@RequestParam String studentName, @RequestParam Long courseId) {
	    	Optional<Course> course = courseService.getCourseById(courseId);

	        if (course.isPresent()) {
	            String message = studentName + " enrolled in course " + courseId;
	            kafkaProducer.sendEnrollmentMessage(message);
	            return ResponseEntity.ok("✅ Enrollment message sent for course: " + courseId);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Course with ID " + courseId + " not found!");
	        }
	    }
	
}
