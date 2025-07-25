package com.employeeauthenticationsystem.dto;

import com.employeeauthenticationsystem.entity.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;


}

