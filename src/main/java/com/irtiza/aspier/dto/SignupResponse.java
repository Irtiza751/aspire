package com.irtiza.aspier.dto;

import com.irtiza.aspier.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
