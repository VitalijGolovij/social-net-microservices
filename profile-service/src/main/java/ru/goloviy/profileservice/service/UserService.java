package ru.goloviy.profileservice.service;

import org.springframework.validation.BindingResult;
import ru.goloviy.profileservice.dto.UserDto;
import ru.goloviy.profileservice.dto.request.GetUserListRequest;
import ru.goloviy.profileservice.model.User;

import java.util.List;

public interface UserService {
    User getUserBy(String username);
    User getUserBy(Long id);
    List<User> getUserList(GetUserListRequest request, BindingResult bindingResult);
}
