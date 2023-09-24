package ru.goloviy.securityservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.goloviy.securityservice.dto.UserDetailsImpl;
import ru.goloviy.securityservice.model.UserCredential;
import ru.goloviy.securityservice.repository.UserCredentialRepository;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserCredentialRepository repository;
    @Autowired
    public UserDetailsServiceImpl(UserCredentialRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> userCredentialOptional = repository.findByUsername(username);
        return userCredentialOptional.map(UserDetailsImpl::new)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("not found user with name '%s'", username)));

    }
}
