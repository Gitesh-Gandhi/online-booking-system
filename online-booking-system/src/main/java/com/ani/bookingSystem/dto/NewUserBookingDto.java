package com.ani.bookingSystem.dto;
import java.time.LocalTime;
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

public class NewUserBookingDto{
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startingTime;   
    private LocalTime endingTime; 
    private Double price;
    private String airConditioning;
    private Integer noOfStops; 
    private String serviceAvailable; 
    private long userId;
}
