package com.bogdanium.social.network.security.services;

import com.bogdanium.social.network.db.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDao repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String password = repository.findPasswordByEmail(email);
        return new User(email, password, Set.of());
    }
}
