package com.employeeauthenticationsystem.service;

import com.employeeauthenticationsystem.dto.UserDto;
import com.employeeauthenticationsystem.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public User registerUser(UserDto userDto);
    public ResponseEntity<Object> getUserByUsername(String username);
    public User updateUserRole(Long id, String changeRole);
}
