package com.employeeauthenticationsystem.service;

import com.employeeauthenticationsystem.dto.EmployeeDto;
import com.employeeauthenticationsystem.entity.Employee;

public interface EmployeeService {
    Employee registerEmployee(EmployeeDto employeeDto);
}
