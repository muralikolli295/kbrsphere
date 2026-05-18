package com.kbrsphere.user_management.model;

import com.kbrsphere.user_management.dto.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @Column(name = "user_id")
        private String userId;
        @Column(name = "user_name")
        private String userName;
        @Column(unique = true,name = "user_email", nullable = false)
        private String userEmail;
        @Column(name = "user_password")
        private String userPassword;
        @Column(name = "phone_number")
        private Long phoneNumber;
        @Enumerated(EnumType.STRING)
        private Role role;
}
