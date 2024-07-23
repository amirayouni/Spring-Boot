package HRnet.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import HRnet.dto.CourseDTO;
import HRnet.entity.Employee;
import HRnet.entity.Course.Course;
import HRnet.entity.Course.CourseStatus;
import HRnet.repository.CourseRepository;
import HRnet.repository.EmployeeRepository;
import HRnet.service.CourseService;
import java.util.Optional;


@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Course saveCourse(Course course) {
        Course savedCourse = courseRepository.save(course);
        
        if (course.getStatus() == CourseStatus.REQUIRED) {
            assignCourseToAllEmployees(savedCourse);
        }
        
        return savedCourse;
    }
    

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }


    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        return modelMapper.map(courseList, new org.modelmapper.TypeToken<List<CourseDTO>>(){}.getType());
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO) {
        if (courseDTO.getCourseId() == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }
        Optional<Course> existingCourse = courseRepository.findById(courseDTO.getCourseId());
        if (existingCourse.isPresent()) {
            Course courseToUpdate = modelMapper.map(courseDTO, Course.class);
            courseRepository.save(courseToUpdate);
            return courseDTO;
        } else {
            throw new RuntimeException("Course not found with id: " + courseDTO.getCourseId());
        }
}

    @Override
    public void deleteCourse(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            courseRepository.delete(course.get());
        } else {
            throw new RuntimeException("Course not found with id: " + courseId);
        }
    }

    @Override
    public void assignCourseToAllEmployees(Course course) {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            if (employee.getCourses() == null) {
                employee.setCourses(new ArrayList<>());
            }
            if (!employee.getCourses().contains(course)) {
                employee.getCourses().add(course);
                employeeRepository.save(employee);  // Save the updated employee entity
            }
        }
}
}