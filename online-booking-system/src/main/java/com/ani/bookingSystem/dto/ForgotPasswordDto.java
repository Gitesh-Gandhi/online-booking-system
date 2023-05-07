package com.ani.bookingSystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordDto {
    
    @NotEmpty(message = "Email should not be empty")
    @NotBlank(message = "Email is mandatory")
    @NotNull(message = "Email should not be null")
    private String email;

}