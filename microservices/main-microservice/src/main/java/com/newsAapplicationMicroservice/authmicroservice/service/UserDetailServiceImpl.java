package com.newsAapplicationMicroservice.authmicroservice.service;

import com.newsAapplicationMicroservice.authmicroservice.entity.UserEntity;
import com.newsAapplicationMicroservice.authmicroservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return mapToUserDetails(userEntity);
    }

    private static UserDetails mapToUserDetails(UserEntity userEntity) {
        List<GrantedAuthority> grantedAuthorities =
                userEntity.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName().toUpperCase(Locale.ROOT)))
                        .collect(Collectors.toList());

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                grantedAuthorities
        );
    }
}
