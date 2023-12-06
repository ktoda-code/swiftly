package com.ktoda.swiftly.backend.user;

import com.ktoda.swiftly.backend.board.Board;
import com.ktoda.swiftly.backend.event.Event;
import com.ktoda.swiftly.backend.password.PasswordService;
import com.ktoda.swiftly.backend.user.dtos.UserCreateRequest;
import com.ktoda.swiftly.backend.user.dtos.UserUpdateRequest;
import com.ktoda.swiftly.backend.user.exceptions.UserAlreadyExistsException;
import com.ktoda.swiftly.backend.user.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository repository;
    @Mock
    private PasswordService passwordService;
    @InjectMocks
    private UserServiceImpl service;

    @Test
    void find_shouldReturnUser() {
        // Given
        User given = new User(
                "U1",
                "Joe",
                "Doe",
                "jdoe@example.com",
                "password123",
                LocalDate.now(),
                null,
                null,
                null
        );
        when(repository.findOne(Example.of(given))).thenReturn(Optional.of(given));

        // When
        User found = service.find(given);

        // Assert
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(given.getId());
        assertThat(found.getEmail()).isEqualTo(given.getEmail());
    }

    @Test
    void find_shouldThrowExceptionWhenUserNotFound() {
        // Given
        User given = new User(
                "U1",
                "Joe",
                "Doe",
                "jdoe@example.com",
                "password123",
                LocalDate.now(),
                null,
                null,
                null
        );
        when(repository.findOne(Example.of(given))).thenReturn(Optional.empty());

        // When / Then
        assertThrows(UserNotFoundException.class, () -> service.find(given));
    }

    @Test
    void findById_shouldReturnUser() {
        // Given
        User given = new User(
                "U1",
                "Joe",
                "Doe",
                "jdoe@example.com",
                "password123",
                LocalDate.now(),
                null,
                null,
                null
        );
        when(repository.findById("U1")).thenReturn(Optional.of(given));

        // When
        User found = service.findById("U1");

        // Assert
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo("U1");
    }

    @Test
    void findById_shouldThrowExceptionWhenNotFound() {
        // Given
        when(repository.findById("U1")).thenReturn(Optional.empty());

        // When / Then
        assertThrows(UserNotFoundException.class, () -> service.findById("U1"));
    }

    @Test
    void findAll_shouldReturnListOfUsers() {
        // Given
        User given_u1 = new User(
                "U1",
                "Joe",
                "Doe",
                "jdoe@example.com",
                "password123",
                LocalDate.now(),
                null,
                null,
                null
        );
        User given_u2 = new User(
                "U2",
                "Joe",
                "Doe",
                "jdoe@example.com",
                "password123",
                LocalDate.now(),
                null,
                null,
                null
        );
        List<User> users = List.of(given_u1, given_u2);
        when(repository.findAll()).thenReturn(users);

        // When
        List<User> result = service.findAll();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo("U1");
        assertThat(result.get(1).getId()).isEqualTo("U2");
    }

    @Test
    void exists_shouldReturnTrueWhenUserExists() {
        // Given
        User given = new User(
                "U1",
                "Joe",
                "Doe",
                "jdoe@example.com",
                "password123",
                LocalDate.now(),
                null,
                null,
                null
        );
        when(repository.exists(Example.of(given))).thenReturn(true);

        // When
        boolean exists = service.exists(given);

        // Assert
        assertThat(exists).isTrue();
    }

    @Test
    void exists_shouldReturnFalseWhenUserDoesNotExist() {
        // Given
        User given = new User(
                "U1",
                "Joe",
                "Doe",
                "jdoe@example.com",
                "password123",
                LocalDate.now(),
                null,
                null,
                null
        );
        when(repository.exists(Example.of(given))).thenReturn(false);

        // When
        boolean exists = service.exists(given);

        // Assert
        assertThat(exists).isFalse();
    }

    @Test
    void save_shouldSaveAndReturnUser() {
        // Given
        User given = new User(
                "U1",
                "Joe",
                "Doe",
                "jdoe@example.com",
                "password123",
                LocalDate.now(),
                null,
                null,
                null
        );
        when(repository.save(given)).thenReturn(given);

        // When
        User saved = service.save(given);

        // Assert
        assertThat(saved).isEqualTo(given);
    }

    @Test
    void remove_shouldRemoveUserWhenExists() {
        // Given
        User given = new User(
                "U1",
                "Joe",
                "Doe",
                "jdoe@example.com",
                "password123",
                LocalDate.now(),
                null,
                null,
                null
        );
        when(repository.exists(Example.of(given))).thenReturn(true);

        // When
        service.remove(given);

        // Then
        verify(repository).delete(given);
    }

    @Test
    void remove_shouldThrowExceptionWhenUserNotFound() {
        // Given
        User given = new User(
                "U1",
                "Joe",
                "Doe",
                "jdoe@example.com",
                "password123",
                LocalDate.now(),
                null,
                null,
                null
        );
        when(repository.exists(Example.of(given))).thenReturn(false);

        // When / Then
        assertThrows(UserNotFoundException.class, () -> service.remove(given));
    }

    @Test
    void findByEmail_shouldReturnUser() {
        // Given
        String email = "jdoe@example.com";
        User given = new User(
                "U1",
                "Joe",
                "Doe",
                "jdoe@example.com",
                "password123",
                LocalDate.now(),
                null,
                null,
                null
        );
        when(repository.findByEmail(email)).thenReturn(Optional.of(given));

        // When
        User found = service.findByEmail(email);

        // Assert
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo(email);
        assertThat(found).isEqualTo(given);
    }

    @Test
    void findByEmail_shouldThrowExceptionWhenNotFound() {
        // Given
        String email = "nonexistent@example.com";
        when(repository.findByEmail(email)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(UserNotFoundException.class, () -> service.findByEmail(email));
    }

    @Test
    void create_shouldCreateNewUser() {
        // Given
        UserCreateRequest createRequest = new UserCreateRequest(
                "John",
                "Doe",
                "johndoe@example.com",
                "Pass@1234"
        );
        User newUser = new User(
                "John",
                "Doe",
                "johndoe@example.com",
                "encryptedPass"
        );
        newUser.addBoard(new Board("My First Board", newUser));
        when(passwordService.encodePassword("Pass@1234")).thenReturn("encryptedPass");
        when(repository.exists(Example.of(newUser))).thenReturn(false);
        when(repository.save(any(User.class))).thenReturn(newUser);

        // When
        User createdUser = service.create(createRequest);

        // Assert
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getEmail()).isEqualTo(createRequest.email());
        assertThat(createdUser.getPassword()).isEqualTo("encryptedPass");
        // Verify the board is added to the new user
        assertThat(createdUser.getBoards())
                .hasSize(1)
                .extracting("name")
                .contains("My First Board");
        assertThat(createdUser
                .getBoards()
                .get(0)
                .getUser())
                .isEqualTo(createdUser);
    }

    @Test
    void create_shouldThrowExceptionWhenUserAlreadyExists() {
        // Given
        UserCreateRequest createRequest = new UserCreateRequest(
                "John",
                "Doe",
                "johndoe@example.com",
                "Pass@1234"
        );
        when(repository.exists(ArgumentMatchers.any())).thenReturn(true);

        // When / Then
        assertThrows(UserAlreadyExistsException.class, () -> service.create(createRequest));
    }

    @Test
    void update_shouldUpdateUserWhenExists() {
        // Given
        UserUpdateRequest updateRequest = new UserUpdateRequest(
                "U1",
                "John",
                "Doe",
                "johndoe@example.com",
                "NewPass123"
        );
        User existingUser = new User(
                "U1",
                "John",
                "Doe",
                "johndoe@example.com",
                "OldPass123",
                LocalDate.now(),
                LocalDate.of(2023, 12, 6),
                null,
                null);
        when(repository.exists(ArgumentMatchers.any())).thenReturn(true);
        when(repository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            existingUser.setFirstName(savedUser.getFirstName());
            existingUser.setLastName(savedUser.getLastName());
            existingUser.setEmail(savedUser.getEmail());
            existingUser.setPassword(savedUser.getPassword());
            return existingUser;
        });

        // When
        User updatedUser = service.update(updateRequest);

        // Assert
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(updateRequest.id());
        assertThat(updatedUser.getFirstName()).isEqualTo(updateRequest.firstName());
        assertThat(updatedUser.getLastName()).isEqualTo(updateRequest.lastName());
        assertThat(updatedUser.getEmail()).isEqualTo(updateRequest.email());
        assertThat(updatedUser.getPassword()).isEqualTo("NewPass123");
    }


    @Test
    void update_shouldThrowExceptionWhenUserNotFound() {
        // Given
        UserUpdateRequest updateRequest = new UserUpdateRequest(
                "U1",
                "John",
                "Doe",
                "johndoe@example.com",
                "NewPass123"
        );
        when(repository.exists(ArgumentMatchers.any())).thenReturn(false);

        // When / Then
        assertThrows(UserNotFoundException.class, () -> service.update(updateRequest));
    }

    @Test
    void addBoard_shouldAddBoardToUserAndSave() {
        // Given
        String userId = "U1";
        User user = new User();
        Board board = new Board();
        when(repository.findById(userId)).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(user);

        // When
        User result = service.addBoard(userId, board);

        // Then
        verify(repository).findById(userId);
        assertThat(result.getBoards()).contains(board);
        verify(repository).save(user);
    }


    @Test
    void removeBoard_shouldRemoveBoardFromUserAndSave() {
        // Given
        String userId = "U1";
        User user = new User();
        Board board = new Board();
        user.addBoard(board);
        when(repository.findById(userId)).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(user);

        // When
        User result = service.removeBoard(userId, board);

        // Then
        verify(repository).findById(userId);
        assertThat(result.getBoards()).doesNotContain(board);
        verify(repository).save(user);
    }


    @Test
    void addEvent_shouldAddEventToUserAndSave() {
        // Given
        String userId = "U1";
        User user = new User();
        Event event = new Event();
        when(repository.findById(userId)).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(user);

        // When
        User result = service.addEvent(userId, event);

        // Then
        verify(repository).findById(userId);
        assertThat(result.getEvents()).contains(event);
        verify(repository).save(user);
    }


    @Test
    void removeEvent_shouldRemoveEventFromUserAndSave() {
        // Given
        String userId = "U1";
        User user = new User();
        Event event = new Event();
        user.addEvent(event);
        when(repository.findById(userId)).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(user);

        // When
        User result = service.removeEvent(userId, event);

        // Then
        verify(repository).findById(userId);
        assertThat(result.getEvents()).doesNotContain(event);
        verify(repository).save(user);
    }

}