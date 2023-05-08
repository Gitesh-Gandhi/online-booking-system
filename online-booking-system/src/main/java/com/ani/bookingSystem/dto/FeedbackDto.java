package com.ani.bookingSystem.dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

public class FeedbackDto {

     private Long id;
    @NotEmpty(message = "Comment cant be empty")
    @NotNull(message = "Comment cant be null")
    @NotBlank(message = "Comment cant be blank")
    private String comment;

    @NotEmpty(message = "Rating cant be empty")
    @NotNull(message = "Rating cant be null")
    @NotBlank(message = "Rating cant be blank")
    private Double rating;

    
}