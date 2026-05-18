package com.kbrsphere.user_management.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private String userId;
    private String userName;
    private String userEmail;
    private Long phoneNumber;
    private Role role;
}
