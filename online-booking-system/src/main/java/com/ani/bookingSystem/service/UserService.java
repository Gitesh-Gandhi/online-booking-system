package com.ani.bookingSystem.service;

import java.util.List;

import com.ani.bookingSystem.dto.FeedbackDto;
import com.ani.bookingSystem.dto.NewUserBookingDto;
import com.ani.bookingSystem.dto.UserBookingDto;
import com.ani.bookingSystem.dto.UsersDto;


public interface UserService {

    UsersDto getUserById(Long userId);

    Integer createNewUserBooking(Long userId, Long bookingId);

    List<UserBookingDto> getAllBookings(Long userId);

    List<UserBookingDto> getCurrentBookings(Long userId);

    List<UserBookingDto> getBookingHistory(Long userId);

    NewUserBookingDto getUserBookingById(Long userId, Long bookingId);

    Integer createFeedback(Long id, FeedbackDto dto);

    List<FeedbackDto> listAllFeedbacks();

    Integer deleteUserBooking(Long bookingId, Long userId);

}
