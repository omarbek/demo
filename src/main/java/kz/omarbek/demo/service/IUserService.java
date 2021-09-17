package kz.omarbek.demo.service;

import kz.omarbek.demo.shared.dto.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);
    UserDto getUser(String email);

}
