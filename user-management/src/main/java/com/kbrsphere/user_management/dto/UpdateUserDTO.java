package com.kbrsphere.user_management.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDTO {

    private String userName;
    private Long phoneNumber;
}