package ru.goloviy.securityservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.goloviy.securityservice.dto.UserDetailsImpl;
import ru.goloviy.securityservice.model.User;
import ru.goloviy.securityservice.repository.UserRepository;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;
    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userCredentialOptional = repository.findByUsername(username);
        return userCredentialOptional.map(UserDetailsImpl::new)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("not found user with name '%s'", username)));

    }
}
