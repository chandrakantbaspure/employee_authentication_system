package com.employeeauthenticationsystem.service;

import com.employeeauthenticationsystem.dao.RoleRepository;
import com.employeeauthenticationsystem.dao.UserRepository;
import com.employeeauthenticationsystem.dto.UserDto;
import com.employeeauthenticationsystem.entity.Role;
import com.employeeauthenticationsystem.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        // Fetch the existing role from the database
        Role userRole = roleRepository.findByRole("USER");
        if (userRole == null) {
            // If the role doesn't exist, create and save it
            userRole = new Role();
            userRole.setRole("USER");
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);
            roleRepository.save(userRole);
        }
        user.setActive(true);
        return userRepository.save(user);
    }

    public ResponseEntity<Object> getUserByUsername(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();

        if (!authenticatedUsername.equals(username)) {
            return ResponseEntity.status(403).body(null);
        }

        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        UserDto userDto = new UserDto();

        if (Objects.equals(user.getUsername(), username)) {
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setPassword(user.getPassword());
            userDto.setRoles(user.getRoles());
        } else {
            throw new RuntimeException("User not found: " + username);
        }

        return ResponseEntity.ok(userDto);
    }

    @Override
    public User updateUserRole(Long id, String changeRole) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role newRole = roleRepository.findByRole(changeRole);
        if (newRole == null) {
            newRole = new Role();
            newRole.setRole(changeRole);
            roleRepository.save(newRole);
        }

        Set<Role> roles = new HashSet<>();
        roles.add(newRole);
        user.setRoles(roles);

        return userRepository.save(user);
    }
}