package com.ani.bookingSystem.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ani.bookingSystem.domain.BookingSlot;
import com.ani.bookingSystem.domain.Users;
import com.ani.bookingSystem.dto.AdminUserBookDto;
import com.ani.bookingSystem.dto.BookingSlotDto;
import com.ani.bookingSystem.dto.LessDetailedBooking;
import com.ani.bookingSystem.dto.UserCreateDto;
import com.ani.bookingSystem.dto.UsersDto;
import com.ani.bookingSystem.exception.BookingSlotNotFoundException;
import com.ani.bookingSystem.exception.UserNotFoundException;
import com.ani.bookingSystem.repository.AdminRepository;
import com.ani.bookingSystem.repository.UsersRepository;
import com.ani.bookingSystem.util.DynamicMapper;

import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private final UsersRepository usersRepository;
    private final AdminRepository adminRepository;
    private final DynamicMapper dynamicMapper;

    // to find all users detail
    @Override
    public List<UsersDto> findUsers() {
        List<UsersDto> listusers = usersRepository.findAll()
                .stream()
                .map(user -> dynamicMapper.convertor(user, new UsersDto()))
                .collect(Collectors.toList());
        if (listusers.isEmpty()) {
            throw new UserNotFoundException("no Users present");
        }
        return listusers;
    }

    public List<UsersDto> findUsersByUserName(String userName) {
        List<UsersDto> listusers = usersRepository.findAllByUserName(userName)
                .stream()
                .map(user -> dynamicMapper.convertor(user, new UsersDto()))
                .collect(Collectors.toList());
        if (listusers.isEmpty()) {
            throw new UserNotFoundException("No users found with username: " + userName);
        }
        return listusers;
    }

    // to find all users details with search
    @Override
    public UsersDto findUserById(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID " + userId));

        UsersDto userDto = new UsersDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    // the admin can delete the user account through this service
    @Override
    public Integer deleteUser(Long id) throws UserNotFoundException {
        usersRepository.deleteById(id);
        return 1;
    }

    // the admin can update the user account through this service
    @Override
    public Integer updateUser(UserCreateDto dto) {
        isUserPresent(dto.getId());
        usersRepository.save(dynamicMapper.convertor(dto, new Users()));
        return 1;
    }

    @Override
    public Integer createBookingSlot(BookingSlotDto createSlot) {
        BookingSlot bookingSlot = dynamicMapper.convertor(createSlot, new BookingSlot());
        adminRepository.save(bookingSlot);
        return 1;

    }

    @Override
    public List<LessDetailedBooking> findBookingSlots() {
        List<LessDetailedBooking> allBookingSlots = adminRepository.findAll()
                .stream()
                .map(bookingSlot -> dynamicMapper.convertor(bookingSlot, new LessDetailedBooking()))
                .collect(Collectors.toList());
        if (allBookingSlots.isEmpty()) {
            throw new BookingSlotNotFoundException("No Booking slots present create new one");
        }
        return allBookingSlots;

    }

    @Override
    public List<LessDetailedBooking> findBookingSlotsByLocation(String location) {
        List<LessDetailedBooking> allBookingSlots = adminRepository.findBookingSlotByLocation(location)
                .stream()
                .map(bookingSlot -> dynamicMapper.convertor(bookingSlot, new LessDetailedBooking()))
                .collect(Collectors.toList());
        if (allBookingSlots.isEmpty()) {
            throw new BookingSlotNotFoundException("No Booking slots present create new one");
        }
        return allBookingSlots;

    }

    @Override
    public List<LessDetailedBooking> findBookingSlotsByStartDate(LocalDate date) {
        List<LessDetailedBooking> allBookingSlots = adminRepository.findBookingSlotByStartDate(date)
                .stream()
                .map(bookingSlot -> dynamicMapper.convertor(bookingSlot, new LessDetailedBooking()))
                .collect(Collectors.toList());
        if (allBookingSlots.isEmpty()) {
            throw new BookingSlotNotFoundException("No Booking slots present create new one");
        }
        return allBookingSlots;

    }

    @Override
    public List<LessDetailedBooking> findBookingSlotsByPrice(Double price) {
        List<LessDetailedBooking> allBookingSlots = adminRepository.findBookingSlotByPrice(price)
                .stream()
                .map(bookingSlot -> dynamicMapper.convertor(bookingSlot, new LessDetailedBooking()))
                .collect(Collectors.toList());
        if (allBookingSlots.isEmpty()) {
            throw new BookingSlotNotFoundException("No Booking slots present create new one");
        }
        return allBookingSlots;

    }

    @Override
    public List<LessDetailedBooking> findBookingSlotsByLocationAndStartDateAndPrice(String location, LocalDate date, Double price) {
        List<LessDetailedBooking> allBookingSlots = adminRepository.findBookingSlotByLocationAndStartDateAndPrice(location , date , price)
                .stream()
                .map(bookingSlot -> dynamicMapper.convertor(bookingSlot, new LessDetailedBooking()))
                .collect(Collectors.toList());
        if (allBookingSlots.isEmpty()) {
            throw new BookingSlotNotFoundException("No Booking slots present create new one");
        }
        return allBookingSlots;

    }

    @Override
    public Integer deleteBookingSlot(Long id) throws BookingSlotNotFoundException {
        adminRepository.deleteById(id);
        return 1;

    }

    @Override
    public Integer updateBookingSlot(BookingSlotDto dto) {
        isBookingSlotPresent(dto.getId());
        adminRepository.save(dynamicMapper.convertor(dto, new BookingSlot()));
        return 1;
    }

    private void isUserPresent(Long id) {
        usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No User found for " + id + " ID"));
    }

    private void isBookingSlotPresent(Long id) {
        adminRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No Booking slot found for " + id + " ID"));
    }

    @Override
    public BookingSlotDto fetchBookingSlotDetails(Long id) {

        BookingSlot bookingSlot = adminRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID " + id));

        BookingSlotDto bookingSlotDto = new BookingSlotDto();
        BeanUtils.copyProperties(bookingSlot, bookingSlotDto);
        return bookingSlotDto;

    }

    public List<AdminUserBookDto> getAllUserBookings() {
        List<AdminUserBookDto> adminUserBookDtos = new ArrayList<>();
        List<Users> users = usersRepository.findAll();
        for (Users user : users) {
            for (BookingSlot bookingSlot : user.getBookingSlots()) {
                AdminUserBookDto adminUserBookDto = new AdminUserBookDto();
                adminUserBookDto.setUserId(user.getId());
                adminUserBookDto.setUserName(user.getUserName());
                adminUserBookDto.setLocation(bookingSlot.getLocation());
                adminUserBookDto.setStartDate(bookingSlot.getStartDate());
                adminUserBookDto.setEndDate(bookingSlot.getEndDate());
                adminUserBookDto.setStartingTime(bookingSlot.getStartingTime());
                adminUserBookDto.setEndingTime(bookingSlot.getEndingTime());
                adminUserBookDto.setPrice(bookingSlot.getPrice());
                adminUserBookDtos.add(adminUserBookDto);
            }
        }
        return adminUserBookDtos;
    }

    public List<AdminUserBookDto> searchUserBookingsByUserName(String userName) {
        List<AdminUserBookDto> adminUserBookDtos = usersRepository.findAllByUserName(userName)
                          .stream()
                          .map(user-> dynamicMapper.convertor(user, new AdminUserBookDto()))
                          .collect(Collectors.toList());
        if(adminUserBookDtos.isEmpty()){
            throw new UserNotFoundException("no user present");
        }
      
        return adminUserBookDtos;

       

       
    }

}