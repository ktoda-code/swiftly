package com.ktoda.swiftly.backend.api.v1;

import com.ktoda.swiftly.backend.dto.user.UserCreateRequest;
import com.ktoda.swiftly.backend.dto.user.UserUpdateRequest;
import com.ktoda.swiftly.backend.model.user.User;
import com.ktoda.swiftly.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@Slf4j
@AllArgsConstructor
public class UserRestController implements ApiInterface<String, UserCreateRequest, UserUpdateRequest> {
    private final UserService service;

    @Override
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<?> findById(@PathVariable("userId") String s) {
        return ResponseEntity.ok(service.findById(s));
    }

    @Override
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UserCreateRequest entity) {
        return new ResponseEntity<>(service.create(entity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(@Valid UserUpdateRequest entity) {
        User user = new User(
                entity.firstName(),
                entity.lastName(),
                entity.email(),
                entity.password()
        );
        user.setId(entity.id());
        return ResponseEntity.ok(service.save(user));
    }

    @Override
    public ResponseEntity<?> delete(String s) {
        User user = new User();
        user.setId(s);
        service.remove(user);
        return ResponseEntity.ok().build();
    }
}
