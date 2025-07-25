package com.employeeauthenticationsystem.service;

import com.employeeauthenticationsystem.dao.EmployeeRepository;
import com.employeeauthenticationsystem.dao.RoleRepository;
import com.employeeauthenticationsystem.dto.EmployeeDto;
import com.employeeauthenticationsystem.entity.Employee;
import com.employeeauthenticationsystem.entity.Role;
import com.employeeauthenticationsystem.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               RoleRepository roleRepository,
                               BCryptPasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Employee registerEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFirstname(employeeDto.getFirstname());
        employee.setLastname(employeeDto.getLastname());
        employee.setEmail(employeeDto.getEmail());
        employee.setUsername(employeeDto.getUsername());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setDesignation(employeeDto.getDesignation());
        employee.setImage(employeeDto.getImage());
        employee.setGender(employeeDto.getGender());

        User user = employeeDto.getUser();
        user.setUsername(employeeDto.getUser().getUsername());
        String encodedPassword = getPasswordEncoder().encode(employeeDto.getUser().getPassword());
        user.setPassword(passwordEncoder.encode(encodedPassword));

        Role role = roleRepository.findByRole("USER");
        if (role == null) {
            log.error("Role not found with role: USER");
            log.info("creating new role USER for: "+employeeDto.getId());
            role = new Role();
            role.setRole("USER");
            roleRepository.save(role);
        }
        user.setRoles(java.util.Collections.singleton(role));
        user.setActive(true);
        employee.setUser(user);

        return employeeRepository.save(employee);
    }
    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
