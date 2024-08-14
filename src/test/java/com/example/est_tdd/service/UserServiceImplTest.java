package com.example.est_tdd.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.est_tdd.entity.User;
import com.example.est_tdd.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock private UserRepository userRepository;

    @InjectMocks private UserServiceImpl userService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
    @Test
    void testRegisterUser() {
        User newUser = new User("test@example.com", "password", "Test User");
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User registeredUser = userService.registerUser("test@example.com", "password", "Test User");

        assertThat(registeredUser).isNotNull();
        assertThat(registeredUser.getEmail()).isEqualTo("test@example.com");
        assertThat(registeredUser.getName()).isEqualTo("Test User");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserByEmail() {
        User user = new User("test@example.com", "password", "Test User");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        User foundUser = userService.getUserByEmail("test@example.com");

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("test@example.com");
        verify(userRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User("test@example.com", "password", "Test User");
        User updatedUser = new User("test@example.com", "password", "Updated User");
        when(userRepository.findByEmail("test@example.com")).thenReturn(existingUser);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser("test@example.com", "Updated User");

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Updated User");
        verify(userRepository, times(1)).findByEmail("test@example.com");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        User user = new User("test@example.com", "password", "Test User");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        userService.deleteUser("test@example.com");

        verify(userRepository, times(1)).findByEmail("test@example.com");
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testIsEmailTaken() {
        when(userRepository.existsByEmail("taken@example.com")).thenReturn(true);
        when(userRepository.existsByEmail("available@example.com")).thenReturn(false);

        boolean isTaken = userService.isEmailToken("taken@example.com");
        boolean isAvailable = userService.isEmailToken("available@example.com");

        assertThat(isTaken).isTrue();
        assertThat(isAvailable).isFalse();
        verify(userRepository, times(1)).existsByEmail("taken@example.com");
        verify(userRepository, times(1)).existsByEmail("available@example.com");
    }

}