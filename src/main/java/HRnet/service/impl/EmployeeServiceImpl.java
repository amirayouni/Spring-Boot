package HRnet.service.impl;

import HRnet.dto.EmployeeDTO;
import HRnet.entity.Employee;
import HRnet.entity.Course.Course;
import HRnet.entity.Course.CourseStatus;
import HRnet.repository.EmployeeRepository;
import HRnet.repository.CourseRepository;
import HRnet.service.EmployeeService;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Employee saveEmployee(Employee employee) {
        employeeRepository.save(modelMapper.map(employee, Employee.class));
        return employee;
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        return modelMapper.map(employeeList, new TypeToken<List<EmployeeDTO>>(){}.getType());
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            return modelMapper.map(employee, Employee.class);
        } else {
            return null; // or throw an exception if preferred
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            Employee employeeToUpdate = modelMapper.map(employee, Employee.class);
            employeeRepository.save(employeeToUpdate);
            return employee;
        }
            else {
                throw new RuntimeException("User not found with id: " + employee.getId());
            }   
    }
    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    @Override
    public void enrollEmployeeInOptionalCourse(Long id, Long courseId) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found"));

        if (course.getStatus() == CourseStatus.OPTIONAL) {
            if (!employee.getCourses().contains(course)) {
                employee.getCourses().add(course);
                employeeRepository.save(employee);
            }
        } else {
            throw new RuntimeException("Course is not optional");
        }
    }

}
