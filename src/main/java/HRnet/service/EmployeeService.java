package HRnet.service;

import HRnet.dto.EmployeeDTO;
import HRnet.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface EmployeeService  {
    Employee saveEmployee(Employee employee);
    Employee getEmployeeByUsername(String username);
    Employee getEmployeeById(Long employeeId);
    List<EmployeeDTO> getAllEmployee();
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Long id);
    public void enrollEmployeeInOptionalCourse(Long employeeId, Long courseId);
}
