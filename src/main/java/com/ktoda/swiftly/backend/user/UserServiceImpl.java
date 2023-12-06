package com.ktoda.swiftly.backend.user;

import com.ktoda.swiftly.backend.event.Event;
import com.ktoda.swiftly.backend.password.PasswordService;
import com.ktoda.swiftly.backend.user.dtos.UserCreateRequest;
import com.ktoda.swiftly.backend.user.dtos.UserUpdateRequest;
import com.ktoda.swiftly.backend.user.exceptions.UserAlreadyExistsException;
import com.ktoda.swiftly.backend.user.exceptions.UserNotFoundException;
import com.ktoda.swiftly.backend.board.Board;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordService passwordService;

    @Override
    public User find(User entity) {
        return repository.findOne(Example.of(entity))
                .orElseThrow(() ->
                        new UserNotFoundException("User not found for entity: " + entity));
    }

    @Override
    public User findById(String id) {
        return repository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean exists(User entity) {
        return repository.exists(Example.of(entity));
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public void remove(User entity) {
        if (exists(entity)) {
            repository.delete(entity);
            return;
        }
        throw new UserNotFoundException("User not found for entity: " + entity);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public User create(UserCreateRequest createRequest) {
        User user = new User(
                createRequest.firstName(),
                createRequest.lastName(),
                createRequest.email(),
                passwordService.encodePassword(createRequest.password())
        );

        if (exists(user)) {
            throw new UserAlreadyExistsException("User already exists with this information");
        }

        user.addBoard(new Board("My First Board", user));

        return save(user);
    }

    @Override
    public User update(UserUpdateRequest updateRequest) {
        User user = new User(
                updateRequest.firstName(),
                updateRequest.lastName(),
                updateRequest.email(),
                updateRequest.password()
        );
        user.setId(updateRequest.id());

        if (!exists(user)) {
            throw new UserNotFoundException("User not found");
        }

        return save(user);
    }

    @Override
    public User addBoard(String userId, Board board) {
        User user = findById(userId);

        user.addBoard(board);
        return save(user);
    }

    @Override
    public User removeBoard(String userId, Board board) {
        User user = findById(userId);

        user.removeBoard(board);
        return save(user);
    }

    @Override
    public User addEvent(String userId, Event event) {
        User user = findById(userId);

        user.addEvent(event);
        return save(user);
    }

    @Override
    public User removeEvent(String userId, Event event) {
        User user = findById(userId);

        user.removeEvent(event);
        return save(user);
    }

}
