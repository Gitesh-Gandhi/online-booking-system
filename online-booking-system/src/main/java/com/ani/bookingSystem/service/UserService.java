package com.ani.bookingSystem.service;

import java.util.List;
import com.ani.bookingSystem.dto.BookingSlotDto;
import com.ani.bookingSystem.dto.FeedbackDto;
import com.ani.bookingSystem.dto.NewUserBookingDto;
import com.ani.bookingSystem.dto.UsersDto;


public interface UserService {

    UsersDto getUserById(Long userId);

    Integer createNewUserBooking(Long userId, Long bookingId);

    List<BookingSlotDto> findUserBookings(Long id);

    NewUserBookingDto getUserBookingById(Long userId, Long bookingId);

    Integer createFeedback(FeedbackDto dto);

    List<FeedbackDto> listAllFeedbacks();

    Integer updateFeedback(FeedbackDto feedbackDto);

    Integer deleteUserBooking(Long bookingId, Long userId);

}
