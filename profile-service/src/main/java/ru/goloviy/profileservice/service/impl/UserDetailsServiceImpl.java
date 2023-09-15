package ru.goloviy.profileservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.goloviy.profileservice.model.User;
import ru.goloviy.profileservice.repository.UserRepository;
import ru.goloviy.profileservice.security.UserDetailsDto;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findUserByUsername(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException(
                    String.format("User with username '%s' not found", username)
            );
        }
        return new UserDetailsDto(user.get());
    }
}
