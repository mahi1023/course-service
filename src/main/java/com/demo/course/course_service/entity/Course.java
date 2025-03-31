package com.demo.course.course_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String title;
	    private String description;
	    private int duration;
	    private String instructorName;
	    private LocalDateTime createdAt = LocalDateTime.now();
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getDuration() {
			return duration;
		}
		public void setDuration(int duration) {
			this.duration = duration;
		}
		public String getInstructorName() {
			return instructorName;
		}
		public void setInstructorName(String instructorName) {
			this.instructorName = instructorName;
		}
		
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		@Override
		public String toString() {
			return "Course [id=" + id + ", title=" + title + ", description=" + description + ", duration=" + duration
					+ ", instructorName=" + instructorName + ", createdAt=" + createdAt + "]";
		}
	
	    
}
