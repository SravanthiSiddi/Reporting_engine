package com.jasper.repository;

import com.jasper.entity.Employee;
import com.jasper.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAll();

    @Query(value="select * from Employee  limit 100000", nativeQuery=true)
    public List<Employee> findByEmpName();


}
