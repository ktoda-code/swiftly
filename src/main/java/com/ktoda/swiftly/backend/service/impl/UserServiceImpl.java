package com.ktoda.swiftly.backend.service.impl;

import com.ktoda.swiftly.backend.dto.user.UserCreateRequest;
import com.ktoda.swiftly.backend.model.user.User;
import com.ktoda.swiftly.backend.repository.UserRepository;
import com.ktoda.swiftly.backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User find(User entity) {
        return repository.findOne(Example.of(entity))
                .orElseThrow(() ->
                        new RuntimeException("User not found for entity: " + entity));
    }

    @Override
    public User findById(String id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found with id: " + id));
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
        if (exists(entity)) {
            return repository.save(entity);
        }
        throw new RuntimeException("User not found for entity: " + entity);
    }

    @Override
    public void remove(User entity) {
        if (exists(entity)) {
            repository.delete(entity);
            return;
        }
        throw new RuntimeException("User not found for entity: " + entity);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found with email: " + email));
    }

    @Override
    public User create(UserCreateRequest createRequest) {
        User user = new User(
                createRequest.firstName(),
                createRequest.lastName(),
                createRequest.email(),
                createRequest.password()
        );
        return save(user);
    }
}
