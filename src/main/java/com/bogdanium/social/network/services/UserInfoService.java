package com.bogdanium.social.network.services;

import com.bogdanium.social.network.components.UserMapper;
import com.bogdanium.social.network.db.dao.UserDao;
import com.bogdanium.social.network.db.entities.UserEntity;
import com.bogdanium.social.network.web.model.request.RequestUser;
import com.bogdanium.social.network.web.model.response.ResponseProfile;
import com.bogdanium.social.network.web.model.response.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseProfile currentProfile() {
        String email = findCurrentUserEmail();

        if (email != null) {
            UserEntity userEntity = userDao.findByEmail(email);
            Integer id = userEntity.getId();
            return userMapper.toResponseProfile(userEntity, userDao.findFriends(id));
        }

        return null;
    }

    private String findCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null
                && authentication.getPrincipal() != null
                && !"anonymousUser".equals(authentication.getPrincipal())) {

            return ((User) authentication.getPrincipal()).getUsername();
        }

        return null;
    }

    public List<ResponseUser> findAllUsers() {
        return userDao.findAll().stream()
                .map(userMapper::toResponseUser)
                .collect(Collectors.toList());
    }

    public ResponseProfile userProfile(int userId) {
        UserEntity userEntity = userDao.findById(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User with id=" + userId + " not found.");
        }

        return userMapper.toResponseProfile(userEntity, userDao.findFriends(userId));
    }

    public void save(RequestUser requestUser) {
        String encodedPassword = passwordEncoder.encode(requestUser.getPassword());
        requestUser.setPassword(encodedPassword);
        UserEntity userEntity = userMapper.toUserEntity(requestUser);
        userEntity = userDao.save(userEntity);
        userMapper.toResponseUser(userEntity);
    }

    public void subscribe(int id) {
        ResponseProfile currentUser = currentProfile();
        userDao.subscribe(currentUser.getId(), id);
    }
}
