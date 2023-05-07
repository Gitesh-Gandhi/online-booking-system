package com.ani.bookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ani.bookingSystem.domain.BookingSlot;


public interface AdminRepository extends JpaRepository<BookingSlot, Long> {

    List<BookingSlot> findBookingSlotByLocation(String ss);

    List <BookingSlot> findByUsers(Long id);
}
