package com.employeeauthenticationsystem.dto;

import com.employeeauthenticationsystem.entity.Role;
import com.employeeauthenticationsystem.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String department;
    private String designation;
    private String image;
    private String gender;
    private User user;
}
