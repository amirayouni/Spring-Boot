package HRnet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HRnet.dto.EmployeeDTO;
import HRnet.entity.Employee;
import HRnet.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/signup", consumes = {"application/xml","application/json"})
    public ResponseEntity<Employee> signup(@RequestBody Employee employee){
        Employee creatEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(creatEmployee,HttpStatus.CREATED);    
    }

    @PostMapping(value = "/signin", consumes = {"application/xml","application/json"})
    public ResponseEntity<Employee> signin(@RequestBody Employee employee) {
        try {
            Employee existingEmployee = employeeService.getEmployeeByUsername(employee.getUsername());
            if (existingEmployee != null && employee.getPassword().equals(existingEmployee.getPassword())) {
                return new ResponseEntity<>(existingEmployee, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error("Error during signin: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping(value = "/update" ,consumes = {"application/xml","application/json"})
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(employee);
            return ResponseEntity.ok(updatedEmployee);
        } catch (Exception e) {
            log.error("Error updating employee: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/courses/{courseId}")
    public ResponseEntity<Void> enrollEmployeeInOptionalCourse(
            @PathVariable("id") Long id,
            @PathVariable("courseId") Long courseId) {
        try {
            employeeService.enrollEmployeeInOptionalCourse(id, courseId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
