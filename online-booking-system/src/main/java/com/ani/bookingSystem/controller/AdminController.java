package com.ani.bookingSystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ani.bookingSystem.domain.BookingSlot;
import com.ani.bookingSystem.dto.AdminUserBookDto;
import com.ani.bookingSystem.dto.AppResponse;
import com.ani.bookingSystem.dto.BookingSlotDto;
import com.ani.bookingSystem.dto.LessDetailedBooking;
import com.ani.bookingSystem.dto.UserCreateDto;
import com.ani.bookingSystem.dto.UsersDto;
import com.ani.bookingSystem.service.AdminService;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    //when admin clicks users

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<UsersDto>>> findAllUsers() {
        List<UsersDto> users = adminService.findUsers();
        AppResponse<List<UsersDto>> response = AppResponse.<List<UsersDto>>builder()
                .sts("success")
                .msg("All users")
                .bd(users)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/filterbyusername", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<UsersDto>>> findAllByName(@RequestParam String userName) {
        List<UsersDto> users = adminService.findUsersByUserName(userName);
        AppResponse<List<UsersDto>> response = AppResponse.<List<UsersDto>>builder()
                .sts("success")
                .msg("All users")
                .bd(users)
                .build();
        return ResponseEntity.ok().body(response);
    }

    //when admin clicks search

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<UsersDto>> findUserByid(@PathVariable Long id) {
        UsersDto user = adminService.findUserById(id);
        AppResponse<UsersDto> response = AppResponse.<UsersDto>builder()
                .sts("success")
                .msg("All users")
                .bd(user)
                .build();
        return ResponseEntity.ok().body(response);
    }
    //when admin clicks delete on specific user

    @DeleteMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> deleteUser(@PathVariable Long id) {

        Integer sts = adminService.deleteUser(id);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
                .sts("success")
                .msg("User Deleted Successfully")
                .bd(sts)
                .build();

        return ResponseEntity.status(200).body(response);

    }
    //when admin clicks update user 

    @PutMapping(value = "/user/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> updateUser(@RequestBody UserCreateDto dto) {

        final Integer sts = adminService.updateUser(dto);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
                .sts("success")
                .msg("user Updated Successfully")
                .bd(sts)
                .build();

        return ResponseEntity.ok().body(response);
    }
    //when admin clicks on create booking

    @PostMapping(value = "/create/bookingslot", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> createBookingSlot(@RequestBody BookingSlotDto dto) {
        final Integer sts = adminService.createBookingSlot(dto);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
                .sts("success")
                .msg("Booking slot Created Successfully")
                .bd(sts)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    //when admin clicks on manage bookings this list will be shown

    @GetMapping(value = "/bookingslot", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<LessDetailedBooking>>> findAllBookingSlots() {
        List<LessDetailedBooking> bookingSlot = adminService.findBookingSlots();
        AppResponse<List<LessDetailedBooking>> response = AppResponse.<List<LessDetailedBooking>>builder()
                .sts("success")
                .msg("All Booking slots")
                .bd(bookingSlot)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/filterLocation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<LessDetailedBooking>>> findBookingSlotsByLocation(@RequestParam String location) {
        List<LessDetailedBooking> sts = adminService.findBookingSlotsByLocation(location);
        AppResponse<List<LessDetailedBooking>> response = AppResponse.<List<LessDetailedBooking>>builder()
                .sts("success")
                .msg("All Booking slots")
                .bd(sts)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/filterbyLocation/{location}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<LessDetailedBooking>>> findBookingByLocation(@PathVariable String location) {
        List<LessDetailedBooking> sts = adminService.findBookingSlotsByLocation(location);
        AppResponse<List<LessDetailedBooking>> response = AppResponse.<List<LessDetailedBooking>>builder()
                .sts("success")
                .msg("All Booking slots")
                .bd(sts)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/filterStartdate/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<LessDetailedBooking>>> findBookingSlotsByStartDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<LessDetailedBooking> sts = adminService.findBookingSlotsByStartDate(date);
        AppResponse<List<LessDetailedBooking>> response = AppResponse.<List<LessDetailedBooking>>builder()
                .sts("success")
                .msg("All Booking slots")
                .bd(sts)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/filterPrice/{price}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<LessDetailedBooking>>> findBookingSlotsByPrice(@PathVariable Double price) {
        List<LessDetailedBooking> sts = adminService.findBookingSlotsByPrice(price);
        AppResponse<List<LessDetailedBooking>> response = AppResponse.<List<LessDetailedBooking>>builder()
                .sts("success")
                .msg("All Booking slots")
                .bd(sts)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/filterbyLSP/{location}/{date}/{price}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<LessDetailedBooking>>> findBookingSlotsByLocationAndStartDateAndPrice(@PathVariable String location, @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @PathVariable Double price) {
        List<LessDetailedBooking> sts = adminService.findBookingSlotsByLocationAndStartDateAndPrice(location, date, price);
        AppResponse<List<LessDetailedBooking>> response = AppResponse.<List<LessDetailedBooking>>builder()
                .sts("success")
                .msg("All Booking slots")
                .bd(sts)
                .build();
        return ResponseEntity.ok().body(response);
    }

    //when admin clicks on view details on specific booking

    @GetMapping(value = "/booking/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<BookingSlotDto>> getBookingByid(@PathVariable Long id) {
        BookingSlotDto sts = adminService.fetchBookingSlotDetails(id);
        AppResponse<BookingSlotDto> response = AppResponse.<BookingSlotDto>builder()
                .sts("success")
                .msg("fetched Booking slot details")
                .bd(sts)
                .build();
        return ResponseEntity.ok().body(response);
    }

    //when admin clicks on delete for specific booking

    @DeleteMapping(value = "/bookingslot/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> deleteBookingSlot(@PathVariable Long id) {

        Integer sts = adminService.deleteBookingSlot(id);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
                .sts("success")
                .msg("Booking slot Deleted Successfully")
                .bd(sts)
                .build();

        return ResponseEntity.status(200).body(response);

    }
    //when admin clicks update booking

    @PutMapping(value = "/bookingslot/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> updateUser(@RequestBody BookingSlotDto dto) {

        final Integer sts = adminService.updateBookingSlot(dto);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
                .sts("success")
                .msg("BookingSlot Updated Successfully")
                .bd(sts)
                .build();

        return ResponseEntity.ok().body(response);
    }
    //when admin clicks view all userbookings

    @GetMapping(value = "/alluserbookings", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<AdminUserBookDto>>> getAllUserBookings() {
        List<AdminUserBookDto> sts = adminService.getAllUserBookings();
        AppResponse<List<AdminUserBookDto>> response = AppResponse.<List<AdminUserBookDto>>builder()
        .sts("success")
        .msg("All User Bookings")
        .bd(sts)
        .build();
          return ResponseEntity.ok().body(response);
    }
    //when admin searches username and clicks search

    @GetMapping(value = "/filterbyname", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<AdminUserBookDto>>> searchUserBookingsByUserName(@RequestParam String username) {
        List<AdminUserBookDto> sts = adminService.searchUserBookingsByUserName(username);
        AppResponse<List<AdminUserBookDto>> response = AppResponse.<List<AdminUserBookDto>>builder()
        .sts("success")
        .msg("All User Bookings")
        .bd(sts)
        .build();
          return ResponseEntity.ok().body(response);

         
    }

         
}