package com.ani.bookingSystem.util;

import org.springframework.stereotype.Component;

import com.ani.bookingSystem.domain.BookingSlot;
import com.ani.bookingSystem.dto.BookingSlotDto;



@Component
public class BookingSlotMapper {
    
    public BookingSlot toDomain(BookingSlotDto dto) {

        return BookingSlot.builder()
                        .id(dto.getId())
                        .location(dto.getLocation())
                        .startDate(dto.getStartDate())
                        .endDate(dto.getEndDate())
                        .startingTime(dto.getStartingTime())
                        .endingTime(dto.getEndingTime())
                        .price(dto.getPrice())
                        .airConditioning(dto.getAirConditioning())
                        .noOfStops(dto.getNoOfStops())
                        .serviceAvailable(dto.getServiceAvailable())
                        .build();
    }

    public BookingSlotDto toDto(BookingSlot domain) {
        return new BookingSlotDto(domain.getId(),
                                 domain.getLocation(),
                                 domain.getStartDate(),
                                 domain.getEndDate(),
                                 domain.getStartingTime(),
                                 domain.getEndingTime(),
                                 domain.getPrice(),
                                 domain.getAirConditioning(),
                                 domain.getNoOfStops(),
                                 domain.getServiceAvailable());
    }
}
