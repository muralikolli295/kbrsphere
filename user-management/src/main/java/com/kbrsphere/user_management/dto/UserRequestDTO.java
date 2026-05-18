package com.kbrsphere.user_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    @NotBlank(message = "userName is required")
    private String userName;

    @NotBlank(message = "userEmail is required")
    @Email(message = "Invalid email format")
    private String userEmail;

    @NotBlank(message = "userPassword is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String userPassword;

    @NotNull(message = "phoneNumber is required")
    private Long phoneNumber;

    @NotNull(message = "role is required")
    private Role role;
}
