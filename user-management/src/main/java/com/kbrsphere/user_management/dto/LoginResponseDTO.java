package com.kbrsphere.user_management.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LoginResponseDTO {

    private String token;
}
