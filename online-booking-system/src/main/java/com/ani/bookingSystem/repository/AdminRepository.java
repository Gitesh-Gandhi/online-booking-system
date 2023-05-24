package com.ani.bookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.bookingSystem.domain.BookingSlot;
import java.time.LocalDate;



public interface AdminRepository extends JpaRepository<BookingSlot, Long> {

    List<BookingSlot> findBookingSlotByLocation(String ss);

    List <BookingSlot> findByUsers(Long id);

    List<BookingSlot> findByEndDateGreaterThan(LocalDate endDate);

    List<BookingSlot> findBookingSlotByStartDate(LocalDate date);

    List<BookingSlot> findBookingSlotByPrice(Double price);

    List<BookingSlot> findBookingSlotByLocationAndStartDateAndPrice(String ss ,LocalDate date ,Double price);

}