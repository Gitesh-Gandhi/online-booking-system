package com.ani.bookingSystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ani.bookingSystem.dto.AppResponse;
import com.ani.bookingSystem.dto.BookingSlotDto;
import com.ani.bookingSystem.dto.FeedbackDto;
import com.ani.bookingSystem.dto.NewUserBookingDto;
import com.ani.bookingSystem.dto.UserBookingDto;
import com.ani.bookingSystem.dto.UserCreateDto;
import com.ani.bookingSystem.dto.UsersDto;
import com.ani.bookingSystem.service.UserService;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/user")
@RestController
public class UserController {

    private final UserService userService;

    //to see user profile

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<UsersDto>> getUserByid(@PathVariable Long id) {
        UsersDto sts = userService.getUserById(id);
        AppResponse<UsersDto> response = AppResponse.<UsersDto>builder()
                .sts("success")
                .msg("fetched users details")
                .bd(sts)
                .build();
        return ResponseEntity.ok().body(response);
    }
    // will be used when user clicks book

    // @CrossOrigin
    // @PostMapping(value = "/createbookingslot", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<AppResponse<Integer>> createNewUserbooking(@RequestBody NewUserBookingDto dto) {
    //     final Integer sts = userService.createNewUserBooking(dto);

    //     final AppResponse<Integer> response = AppResponse.<Integer>builder()
    //             .sts("success")
    //             .msg("user booking Created Successfully")
    //             .bd(sts)
    //             .build();
    //     return ResponseEntity.status(HttpStatus.CREATED).body(response);

    // }

    @PostMapping(value = "/{userId}/booking/{bookingId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> createNewUserBooking(@Valid @PathVariable Long userId, @PathVariable Long bookingId) {
        Integer sts = userService.createNewUserBooking(userId, bookingId);
        AppResponse<Integer> response = AppResponse.<Integer>builder()
                .sts("success")
                .msg("new event booked successfully.")
                .bd(sts)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //will be used in current bookings

    // @CrossOrigin
    // @GetMapping(value = "/userbooking/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<AppResponse<List<BookingSlotDto>>> findUserByid(@PathVariable Long id) {
    //     List<BookingSlotDto> sts = userService.findUserBookings(id);
    //     AppResponse<List<BookingSlotDto>> response = AppResponse.<List<BookingSlotDto>>builder()
    //             .sts("success")
    //             .msg("All userbookings")
    //             .bd(sts)
    //             .build();
    //     return ResponseEntity.ok().body(response);
    // }

    @GetMapping(value = "/getuserbookings/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<UserBookingDto>>> findAll(@PathVariable Long userId) {
        List<UserBookingDto> sts=userService.getAllBookings(userId);
        AppResponse<List<UserBookingDto>> response=AppResponse.<List<UserBookingDto>>builder()
                    .sts("success")
                    .msg("All  bookings")
                    .bd(sts)
                    .build();
              return ResponseEntity.ok().body(response);
    }

   
    @GetMapping(value = "/getcurrentbookings/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<UserBookingDto>>> findAllCurrent(@PathVariable Long userId) {
        List<UserBookingDto> sts=userService.getCurrentBookings(userId);
        AppResponse<List<UserBookingDto>> response=AppResponse.<List<UserBookingDto>>builder()
                    .sts("success")
                    .msg("All current bookings")
                    .bd(sts)
                    .build();
              return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/getbookinghistory/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<UserBookingDto>>> findAllHistory(@PathVariable Long userId) {
        List<UserBookingDto> sts=userService.getBookingHistory(userId);
        AppResponse<List<UserBookingDto>> response=AppResponse.<List<UserBookingDto>>builder()
                    .sts("success")
                    .msg("All bookings history")
                    .bd(sts)
                    .build();
              return ResponseEntity.ok().body(response);
    }


    
    //will be used in view details

    @GetMapping(value = "/userbooking/{userId}/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <AppResponse<NewUserBookingDto>> getUserBookingById(@PathVariable Long userId,@PathVariable Long bookingId) {
        NewUserBookingDto sts = userService.getUserBookingById(userId, bookingId);
        AppResponse <NewUserBookingDto> response = AppResponse.<NewUserBookingDto>builder()
                .sts("success")
                .msg("fetched userbookings details")
                .bd(sts)
                .build();
        return ResponseEntity.ok().body(response);
    }
    //will be used when delete button is clicked

    @DeleteMapping(value = "/{userId}/booking/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> deleteUserBooking(@PathVariable Long userId,
            @PathVariable Long bookingId) {
        final Integer sts = userService.deleteUserBooking(bookingId, userId);
        final AppResponse<Integer> response = AppResponse.<Integer>builder()
                .sts("success")
                .msg("user booking Deleted Successfully")
                .bd(sts)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    //will be used when user clicks submit feedback

    // @CrossOrigin
    // @PostMapping(value = "/feedback/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<AppResponse<Integer>> createUserFeedback(@RequestBody FeedbackDto dto) {
    //     final Integer sts = userService.createFeedback(dto);
    //     final AppResponse<Integer> response = AppResponse.<Integer>builder()
    //             .sts("success")
    //             .msg("Feedback submitted Successfully")
    //             .bd(sts)
    //             .build();
    //     return ResponseEntity.status(HttpStatus.CREATED).body(response);

    // }

    @PostMapping(value = "/{userId}/feedback", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> createFeedback(@Valid @PathVariable Long userId, @RequestBody FeedbackDto dto) {
        Integer sts = userService.createFeedback(userId,dto);
        AppResponse<Integer> response = AppResponse.<Integer>builder()
                .sts("success")
                .msg("feedback submitted.")
                .bd(sts)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    //will be used when user clicks show feedbacks

    @GetMapping(value = "/feedback", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<FeedbackDto>>> getAllFeedbacks() {
        List<FeedbackDto> sts = userService.listAllFeedbacks();
        AppResponse<List<FeedbackDto>> response = AppResponse.<List<FeedbackDto>>builder()
                .sts("success")
                .msg("All Feedbacks")
                .bd(sts)
                .build();
        return ResponseEntity.ok().body(response);
    }

    // @PutMapping(value = "/feedback/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<AppResponse<Integer>> updateFeedback(@RequestBody FeedbackDto dto) {
    //     final Integer sts = userService.updateFeedback(dto);
    //     final AppResponse<Integer> response = AppResponse.<Integer>builder()
    //             .sts("success")
    //             .msg("Feedback updated Successfully")
    //             .bd(sts)
    //             .build();
    //     return ResponseEntity.status(HttpStatus.CREATED).body(response);

    // }

}