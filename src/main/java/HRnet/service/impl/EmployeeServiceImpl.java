package HRnet.service.impl;

import HRnet.dto.EmployeeDTO;
import HRnet.entity.Employee;
import HRnet.repository.EmployeeRepository;
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
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO.getId() == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }
        Optional<Employee> existingEmployee = employeeRepository.findById(employeeDTO.getId());
        if (existingEmployee.isPresent()) {
            Employee employeeToUpdate = modelMapper.map(employeeDTO, Employee.class);
            employeeRepository.save(employeeToUpdate);
            return employeeDTO;
        }
            else {
                throw new RuntimeException("User not found with id: " + employeeDTO.getId());
            }   
    }
    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
