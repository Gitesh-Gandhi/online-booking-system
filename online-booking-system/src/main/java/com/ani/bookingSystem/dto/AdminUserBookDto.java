package com.ani.bookingSystem.dto;

import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter

public class AdminUserBookDto{
    @NotEmpty(message = "location cant be empty")
    @NotNull(message = "location cant be null")
    @NotBlank(message = "location cant be blank")
    private String location;

    @NotEmpty(message = "startDate cant be empty")
    @NotNull(message = "startDate cant be null")
    @NotBlank(message = "startDate cant be blank")
    private LocalDate startDate;

    @NotEmpty(message = "endDate cant be empty")
    @NotNull(message = "endDate cant be null")
    @NotBlank(message = "endDate cant be blank")
    private LocalDate endDate;

    @NotEmpty(message = "startingTime cant be empty")
    @NotNull(message = "startingTime cant be null")
    @NotBlank(message = "startingTime cant be blank")
    private LocalTime startingTime;
    
    @NotEmpty(message = "endingTime cant be empty")
    @NotNull(message = "endingTime cant be null")
    @NotBlank(message = "endingTime cant be blank")
    private LocalTime endingTime; 

    @NotEmpty(message = "price cant be empty")
    @NotNull(message = "price cant be null")
    @NotBlank(message = "price cant be blank")
    private Double price;

    @NotEmpty(message = "Username cant be empty")
    @NotNull(message = "Username cant be null")
    @NotBlank(message = "Username cant be blank")
    private String userName;

    @NotEmpty(message = "userId cant be empty")
    @NotNull(message = "userId cant be null")
    @NotBlank(message = "userId cant be blank")
    private long userId;
}