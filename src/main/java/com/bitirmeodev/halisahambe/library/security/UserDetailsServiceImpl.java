package com.bitirmeodev.halisahambe.library.security;

import com.bitirmeodev.halisahambe.domain.auth.user.impl.User;
import com.bitirmeodev.halisahambe.domain.auth.user.impl.UserRepository;
import com.bitirmeodev.halisahambe.library.enums.MessageCodes;
import com.bitirmeodev.halisahambe.library.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username).orElseThrow(() -> new BaseException(MessageCodes.ENTITY_NOT_FOUND, User.class,username));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.singleton(new SimpleGrantedAuthority(getRole(user.getUserType().getRole()))))
                .build();
    }

    private String getRole(String role) {
        return "ROLE_" + role;
    }
}
