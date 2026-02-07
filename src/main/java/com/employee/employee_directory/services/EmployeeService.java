package com.employee.employee_directory.services;

import com.employee.employee_directory.entity.Employee;
import com.employee.employee_directory.repository.EmployeeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

/*Type	Correct class
Page	org.springframework.data.domain.Page
Pageable	org.springframework.data.domain.Pageable
Repo	JpaRepository*/



@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> getAllEmployees(Pageable pageable){

        return employeeRepository.findAll(pageable);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee){

        Employee emp = getEmployeeById(id);

        if (emp.getName() != null && !emp.getName().isBlank()){
            emp.setName(employee.getName());
        }
        if (emp.getAge() != null){
            emp.setAge(employee.getAge());
        }
        if (emp.getGender() != null && !emp.getGender().isBlank()){
            emp.setGender(employee.getGender());
        }
        if (emp.getSalary() != null){
            emp.setSalary(employee.getSalary());
        }

        return employeeRepository.save(emp);

    }

    public void deleteEmployee(Long id){
        Employee emp = getEmployeeById(id);
        employeeRepository.delete(emp);
    }

    public Page<Employee> getEmployeeByGender(String gender, Pageable pageable){
        return employeeRepository.findAllByGender(gender, pageable);
    }

    public Integer getEmployeeCountByAddressAndAge(String address, Integer age){
        return employeeRepository.countAllByAddressContainingIgnoreCaseAndAgeLessThan(address, age);
    }

    public List<Employee> getEmployeeByAddressAndSalary(String address, Integer salary){
        return employeeRepository.findByAddressAndSalary(address, salary);
    }

    @Transactional
    public void deleteEmployeeBySalary(Integer salary){
        employeeRepository.deleteEmployeeBySalaryGreaterThan(salary);
    }
}
