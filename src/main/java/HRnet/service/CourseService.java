package HRnet.service;

import org.springframework.stereotype.Service;
import java.util.List;

import HRnet.dto.CourseDTO;
import HRnet.entity.Course.Course;
import jakarta.transaction.Transactional;

@Service
@Transactional
public interface CourseService {
    Course saveCourse(Course course);
    Course getCourseById(Long courseId);
    List<CourseDTO> getAllCourses();
    CourseDTO updateCourse(CourseDTO courseDTO);
    void deleteCourse(Long courseId);
    void assignCourseToAllEmployees(Course course);

}
