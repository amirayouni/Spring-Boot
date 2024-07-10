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
    List<EmployeeDTO> getAllEmployee();
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
}
