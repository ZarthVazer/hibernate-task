package edu.epam.fop.web.service;

import edu.epam.fop.web.jpa.entity.Course;
import edu.epam.fop.web.jpa.repository.CourseRepository;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Adilov Abdubosit
 */
public class CourseService {

    private final CourseRepository courseRepository = new CourseRepository();

    public Course updateStartAt(long courseId, OffsetDateTime startAt) {
        Course course = getCourse(courseId);
        Course.validatePeriod(startAt, course.getEndAt());
        course.setStartAt(startAt);
        return courseRepository.update(courseId, course);
    }

    public Course updateEndAt(long courseId, OffsetDateTime endAt) {
        Course course = getCourse(courseId);
        Course.validatePeriod(course.getStartAt(), endAt);
        course.setEndAt(endAt);
        return courseRepository.update(courseId, course);
    }

    public List<Course> findActiveCourses() {
        return courseRepository.findActive(OffsetDateTime.now());
    }

    public List<Course> findFutureCourses() {
        return courseRepository.findFuture(OffsetDateTime.now());
    }

    private Course getCourse(long courseId) {
        Course course = courseRepository.find(courseId);
        if (course == null) {
            throw new IllegalArgumentException("Course not found: " + courseId);
        }
        return course;
    }
}
