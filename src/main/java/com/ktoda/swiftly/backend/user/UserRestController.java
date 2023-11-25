package com.ktoda.swiftly.backend.user;

import com.ktoda.swiftly.backend.common.api.ApiInterface;
import com.ktoda.swiftly.backend.user.dtos.UserCreateRequest;
import com.ktoda.swiftly.backend.user.dtos.UserUpdateRequest;
import com.ktoda.swiftly.backend.user.User;
import com.ktoda.swiftly.backend.user.UserService;
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

    @GetMapping
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(service.findByEmail(email));
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
    public ResponseEntity<?> update(@Valid @RequestBody UserUpdateRequest entity) {
        return ResponseEntity.ok(service.update(entity));
    }

    @Override
    public ResponseEntity<?> delete(String id) {
        User user = new User();
        user.setId(id);
        service.remove(user);
        return ResponseEntity.ok().build();
    }
}
