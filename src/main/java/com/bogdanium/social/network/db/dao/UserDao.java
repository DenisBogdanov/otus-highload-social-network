package com.bogdanium.social.network.db.dao;

import com.bogdanium.social.network.db.entities.UserEntity;
import com.bogdanium.social.network.db.entities.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<UserEntity> findAll() {
        return jdbcTemplate.query("select * from users",
                USER_ENTITY_ROW_MAPPER);
    }

    public UserEntity save(UserEntity user) {
        jdbcTemplate.getJdbcTemplate().update(
                "insert into users (email, password, first_name, last_name, gender, age, interests, city)" +
                        " values(?,?,?,?,?,?,?,?)",
                user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getGender().name(),
                user.getAge(), user.getInterests(), user.getCity());

        return findByEmail(user.getEmail());
    }

    public String findPasswordByEmail(String email) {
        return jdbcTemplate.queryForObject("select password from users where email = :email",
                Map.of("email", email), String.class);
    }

    public UserEntity findByEmail(String email) {
        return jdbcTemplate.queryForObject("select * from users where email = :email",
                Map.of("email", email),
                USER_ENTITY_ROW_MAPPER);
    }

    public UserEntity findById(int id) {
        return jdbcTemplate.queryForObject("select * from users where id = :id",
                Map.of("id", id),
                USER_ENTITY_ROW_MAPPER);
    }

    public List<UserEntity> findFriends(int id) {
        return jdbcTemplate.query(
                "select * from users where id in " +
                        "(select friend_id from users_friends where user_id=:id)",
                Map.of("id", id),
                USER_ENTITY_ROW_MAPPER
        );
    }

    public void subscribe(int subscriberId, int subscriptionId) {
        jdbcTemplate.getJdbcTemplate().update(
                "insert into users_friends (user_id, friend_id) values(?,?)", subscriberId, subscriptionId);
    }

    private final static RowMapper<UserEntity> USER_ENTITY_ROW_MAPPER = (rs, rowNum) -> UserEntity.builder()
            .id(rs.getInt("id"))
            .email(rs.getString("email"))
            .firstName(rs.getString("first_name"))
            .lastName(rs.getString("last_name"))
            .gender(Gender.get(rs.getString("gender")))
            .city(rs.getString("city"))
            .interests(rs.getString("interests"))
            .age(rs.getInt("age"))
            .build();
}
