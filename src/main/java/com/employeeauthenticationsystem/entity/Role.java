package com.employeeauthenticationsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles") // Renamed to 'roles' to maintain consistency
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name ="role_name" ,unique = true)
    private String role; // e.g., ROLE_USER, ROLE_ADMIN, ROLE_SUPER_USER

    public Role(String user) {
    }
}
