/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 */

// Write your code here

package com.example.employee.service;

import com.example.employee.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.employee.model.*;
import java.util.*;

@Service
public class EmployeeJpaService implements EmployeeRepository {
    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employee = employeeJpaRepository.findAll();
        return employee;
    }

    @Override
    public Employee addEmployee(Employee employee) {

        Employee savedEmployee = employeeJpaRepository.save(employee);

        return savedEmployee;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        try {

            Employee employee = employeeJpaRepository.findById(employeeId).get();

            return employee;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee employee) {

        try {
            Employee toUpdate = employeeJpaRepository.findById(employeeId).get();
            if (employee.getEmployeeName() != null) {
                toUpdate.setEmployeeName(employee.getEmployeeName());
            }
            if (employee.getEmail() != null) {
                toUpdate.setEmail(employee.getEmail());
            }
            if (employee.getDepartment() != null) {
                toUpdate.setDepartment(employee.getDepartment());
            }
            employeeJpaRepository.save(toUpdate);
            return toUpdate;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteEmployee(int employeeId) {

        try{
            employeeJpaRepository.deleteById(employeeId)
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
