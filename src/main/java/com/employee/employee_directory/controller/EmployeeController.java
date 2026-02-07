package com.employee.employee_directory.controller;

import com.employee.employee_directory.entity.Employee;
import com.employee.employee_directory.services.EmployeeService;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

import java.util.List;


@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/gender/{gender}")
    public ResponseEntity<Page<Employee>> getEmployeeByGender(
            @PathVariable String gender,
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam String sortBy){

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return ResponseEntity.ok(employeeService.getEmployeeByGender(gender, pageable));
    }

    @GetMapping("/count-by-ang")
    public ResponseEntity<Integer> getEmployeeCountByAddressAndAge(
            @RequestParam String address,
            @RequestParam Integer age){
        return ResponseEntity.ok(employeeService.getEmployeeCountByAddressAndAge(address, age));
    }


    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployee(@RequestParam int size, @RequestParam int page, @RequestParam String sortBy){

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return ResponseEntity.ok(employeeService.getAllEmployees(pageable));
    }

    @GetMapping("/by-address-and-salary")
    public ResponseEntity<List<Employee>> getEmployeeByAddressAndSalary(
            @RequestParam String address, @RequestParam Integer salary){
        return ResponseEntity.ok(employeeService.getEmployeeByAddressAndSalary(address, salary));
    }


    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/by-salary/{salary}")
    public ResponseEntity<Void> deleteEmployeeBySalary(@PathVariable Integer salary){
        employeeService.deleteEmployeeBySalary(salary);
        return ResponseEntity.ok().build();
    }




}
