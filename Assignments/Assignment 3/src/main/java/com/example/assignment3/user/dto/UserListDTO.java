package com.example.assignment3.user.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
public class UserListDTO extends UserMinimalDTO {
    private String email;
    private Set<String> roles;
    private String password;

}


