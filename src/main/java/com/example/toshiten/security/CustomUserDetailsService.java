package com.example.toshiten.security;

import com.example.toshiten.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.selectByUsername(username)
                .map(r -> new User(
                                r.username(),
                                r.password(),
                                List.of()
                        )
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
