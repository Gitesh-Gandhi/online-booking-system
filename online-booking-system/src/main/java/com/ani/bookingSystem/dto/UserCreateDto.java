package com.ani.bookingSystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserCreateDto {
    private Long id;
    @NotEmpty(message = "Username cant be empty")
    @NotNull(message = "Username cant be null")
    @NotBlank(message = "Username cant be blank")
    private String userName;

    @NotEmpty(message = "Email cant be empty")
    @NotNull(message = "Email cant be null")
    @NotBlank(message = "Email cant be blank")
    private String email;

    @NotEmpty(message = "Password cant be empty")
    @NotNull(message = "Password cant be null")
    @NotBlank(message = "Password cant be blank")
    private String password;

    @NotEmpty(message = "Current location cant be empty")
    @NotNull(message = "Current location cant be null")
    @NotBlank(message = "Current location cant be blank")
    private String currentLocation;
    
    @NotEmpty(message = "Role is mandatory")
    @NotNull(message = "Role is mandatory")
    @NotBlank(message = "Role cant be blank")
    private String role;
}
