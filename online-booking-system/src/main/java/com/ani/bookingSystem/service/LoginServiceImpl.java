package com.ani.bookingSystem.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ani.bookingSystem.domain.Users;
import com.ani.bookingSystem.dto.ForgotPasswordDto;
import com.ani.bookingSystem.dto.LoginDto;
import com.ani.bookingSystem.dto.LoginResponseDto;
import com.ani.bookingSystem.dto.UserCreateDto;
import com.ani.bookingSystem.exception.UserNotFoundException;
import com.ani.bookingSystem.repository.UsersRepository;
import com.ani.bookingSystem.util.DynamicMapper;


import lombok.AllArgsConstructor;

@Transactional
@AllArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

  private final UsersRepository usersRepository;
  private final DynamicMapper dynamicMapper;

  @Override
  public Integer createUser(UserCreateDto user) {
    Users users = dynamicMapper.convertor(user, new Users());
    usersRepository.save(users);
    return 1;
  }

  @Override
  public String loginUser(LoginDto dto) {
    Optional<Users> op = usersRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
    Users user = op.orElseThrow(() -> new UserNotFoundException("Email/Password is not valid"));
    return user.getRole();
  }

  @Override
  public String forgotPassword(ForgotPasswordDto dto) {
    Optional<Users> op = usersRepository.findByEmail(dto.getEmail());
    op.orElseThrow(() -> new UserNotFoundException("Email Not Found"));

    return "A link has been sent on this email";
  }
  @Override
  public LoginResponseDto loginUserForResponse(LoginDto dto) {
      Optional<Users> op = usersRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

      Users user = op.orElseThrow(() -> new UserNotFoundException("Email/Password is not valid"));
      return dynamicMapper.convertor(user, new LoginResponseDto());
  }
}
