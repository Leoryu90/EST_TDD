package com.example.est_tdd.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    @DisplayName("유저 생성 테스트")
    public void testUserCreation() {

        User user = new User("thetein@gmail.com", "1234", "ryu seokho");

        assertThat(user.getEmail()).isEqualTo("thetein@gmail.com");
        assertThat(user.getPassword()).isEqualTo("1234");
        assertThat(user.getName()).isEqualTo("ryu seokho");
        assertThat(user.getId()).isNull();

    }
}
