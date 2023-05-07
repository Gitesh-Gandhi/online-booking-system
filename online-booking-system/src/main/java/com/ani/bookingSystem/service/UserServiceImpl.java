package com.ani.bookingSystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ani.bookingSystem.domain.BookingSlot;
import com.ani.bookingSystem.domain.Feedback;
import com.ani.bookingSystem.domain.Users;
import com.ani.bookingSystem.dto.BookingSlotDto;
import com.ani.bookingSystem.dto.FeedbackDto;
import com.ani.bookingSystem.dto.NewUserBookingDto;
import com.ani.bookingSystem.dto.UsersDto;
import com.ani.bookingSystem.exception.BookingSlotNotFoundException;
import com.ani.bookingSystem.exception.FeedbackNotFoundException;
import com.ani.bookingSystem.exception.InvalidRoleException;
import com.ani.bookingSystem.exception.UserNotFoundException;
import com.ani.bookingSystem.repository.AdminRepository;
import com.ani.bookingSystem.repository.FeedbackRepository;
import com.ani.bookingSystem.repository.UsersRepository;
import org.springframework.beans.BeanUtils;

import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
@Service

public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final AdminRepository adminRepository;
    private final FeedbackRepository feedbackRepository;

    @Override
    public UsersDto getUserById(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID " + userId));

        UsersDto userDto = new UsersDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    // @Override
    // public Integer createNewUserBooking(NewUserBookingDto dto) {
    //     Users user = usersRepository.findById(dto.getUserId())
    //             .orElseThrow(() -> new UserNotFoundException("No Id found"));
    //     BookingSlot bookingSlot = new BookingSlot();
    //     BeanUtils.copyProperties(dto, bookingSlot);
    //     bookingSlot.getUsers().add(user);
    //     adminRepository.save(bookingSlot);
    //     return 1;

    // }

    @Override
    public Integer createNewUserBooking(Long userId, Long bookingId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        // if (user.getRole().equals("admin"))
        //     throw new InvalidRoleException("Admin can't book Event");
        BookingSlot booking = adminRepository.findById(bookingId)
                .orElseThrow(() -> new BookingSlotNotFoundException("Event not Found for " + bookingId + " id"));
        user.getBookingSlots().add(booking);
        usersRepository.save(user);
        return 1;
    }

    public List<BookingSlotDto> findUserBookings(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No Id found"));
        List<BookingSlotDto> bookingSlotDtos = new ArrayList<>();
        for (BookingSlot bookingSlot : user.getBookingSlots()) {
            BookingSlotDto bookingSlotDto = new BookingSlotDto();
            BeanUtils.copyProperties(bookingSlot, bookingSlotDto);
            bookingSlotDtos.add(bookingSlotDto);
            if(bookingSlotDtos.isEmpty()){
                throw new BookingSlotNotFoundException("no Booking slot present");
            }
        }
        return bookingSlotDtos;
    } 
    
    public NewUserBookingDto getUserBookingById(Long userId, Long bookingId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No user found with id " + userId));
        
        BookingSlot bookingSlot = user.getBookingSlots().stream()
                .filter(bs -> bs.getId().equals(bookingId))
                .findFirst()
                .orElseThrow(() -> new BookingSlotNotFoundException("No booking slot found with id " + bookingId));
        
                NewUserBookingDto bookingSlotDto = new NewUserBookingDto();
        BeanUtils.copyProperties(bookingSlot, bookingSlotDto);
        
        return bookingSlotDto;
    }

    @Override
    public Integer deleteUserBooking(Long bookingId, Long userId) {
        BookingSlot bookingSlot = adminRepository.findById(bookingId)
                .orElseThrow(() -> new BookingSlotNotFoundException("No user booking slot found" ));
        Users user = bookingSlot.getUsers().stream().filter(u -> u.getId().equals(userId)).findFirst()
                .orElseThrow(() -> new UserNotFoundException("No user booking slot found"));

        bookingSlot.getUsers().remove(user);
        user.getBookingSlots().remove(bookingSlot);
        adminRepository.save(bookingSlot);
        return 1;
    }

    public Integer createFeedback( FeedbackDto feedbackDto) {
        Users user = usersRepository.findById(feedbackDto.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("No user found"));
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(feedbackDto, feedback);
        feedback.setUsers(user);
        feedbackRepository.save(feedback);
        return 1;
    }

    public List<FeedbackDto> listAllFeedbacks() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        List<FeedbackDto> feedbackDtoList = new ArrayList<>();
    
        for (Feedback feedback : feedbackList) {
            FeedbackDto feedbackDto = new FeedbackDto();
            BeanUtils.copyProperties(feedback, feedbackDto);
            feedbackDtoList.add(feedbackDto);
            if(feedbackDtoList.isEmpty()){
                throw new FeedbackNotFoundException("no feedback present ");
            }
        }
    
        return feedbackDtoList;
    }
    public Integer updateFeedback( FeedbackDto feedbackDto) {
        Feedback feedback = feedbackRepository.findById(feedbackDto.getUserId())
                .orElseThrow(() -> new FeedbackNotFoundException("No feedback found"));
        BeanUtils.copyProperties(feedbackDto, feedback);
        feedbackRepository.save(feedback);
        return 1;
    }
    

}