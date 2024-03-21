package ru.sushchenko.metrics_producer.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.sushchenko.metrics_producer.entity.User;
import ru.sushchenko.metrics_producer.repo.UserRepo;
import ru.sushchenko.metrics_producer.utils.exception.UserNotFoundException;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User doesn't exist"));
        return UserPrincipal.builder()
                .user(user)
                .build();
    }
}
