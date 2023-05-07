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
public class UsersDto {
    private Long id;

    @NotEmpty(message = "Username cant be empty")
    @NotNull(message = "Username cant be null")
    @NotBlank(message = "Username cant be blank")
    private String userName;

    @NotEmpty(message = "Email cant be empty")
    @NotNull(message = "Email cant be null")
    @NotBlank(message = "Email cant be blank")
    private String email;

  

    @NotEmpty(message = "Current location cant be empty")
    @NotNull(message = "Current location cant be null")
    @NotBlank(message = "Current location cant be blank")
    private String currentLocation;

} 