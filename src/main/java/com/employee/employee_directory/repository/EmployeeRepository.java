package com.employee.employee_directory.repository;

import com.employee.employee_directory.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findAllByGender(String gender, Pageable pageable);

    Integer countAllByAddressContainingIgnoreCaseAndAgeLessThan(String address, Integer age);

    @Query("SELECT e from Employee e WHERE e.address LIKE %?1% AND e.salary = ?2")
    List<Employee> findByAddressAndSalary(String address, Integer salary);

    void deleteEmployeeBySalaryGreaterThan(Integer salary);
}
