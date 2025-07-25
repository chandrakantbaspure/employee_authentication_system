package com.employeeauthenticationsystem.controller;

import com.employeeauthenticationsystem.dto.EmployeeDto;
import com.employeeauthenticationsystem.dto.UserDto;
import com.employeeauthenticationsystem.entity.Employee;
import com.employeeauthenticationsystem.entity.User;
import com.employeeauthenticationsystem.service.EmployeeService;
import com.employeeauthenticationsystem.service.UserService;
import com.employeeauthenticationsystem.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    private final EmployeeService employeeService;

    @Autowired
    public UserController(UserServiceImpl userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @PostMapping("/employee/register")
    public ResponseEntity<Employee> registerEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee registeredEmployee = employeeService.registerEmployee(employeeDto);
        return ResponseEntity.ok(registeredEmployee);
    }

    @PostMapping("/user/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        User registeredUser = userService.registerUser(userDto);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/user/getUsername/{username}")
    public ResponseEntity<ResponseEntity<Object>> getUser(@PathVariable String username) {
        ResponseEntity<Object> userDto = userService.getUserByUsername(username);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/user/updateRole/{id}/{changeRole}")
    public ResponseEntity<User> updateUserRole(@PathVariable Long id,@PathVariable String changeRole) {
        User updatedUser = userService.updateUserRole(id,changeRole);
        return ResponseEntity.ok(updatedUser);
    }
}