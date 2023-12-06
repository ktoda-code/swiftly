package com.ktoda.swiftly.backend.user;

import com.ktoda.swiftly.backend.board.Board;
import com.ktoda.swiftly.backend.board.BoardCreateRequest;
import com.ktoda.swiftly.backend.common.api.ApiInterface;
import com.ktoda.swiftly.backend.event.Event;
import com.ktoda.swiftly.backend.event.EventCreateRequest;
import com.ktoda.swiftly.backend.user.dtos.UserCreateRequest;
import com.ktoda.swiftly.backend.user.dtos.UserUpdateRequest;
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

    @GetMapping("/email")
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

    @PostMapping("/{userId}/boards")
    public ResponseEntity<?> addBoard(@PathVariable("userId") String userId,
                                      @Valid @RequestBody Board board) {
        return new ResponseEntity<>(service.addBoard(userId, board), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/boards/{boardId}")
    public ResponseEntity<?> removeBoard(@PathVariable("userId") String userId,
                                         @PathVariable("boardId") Long boardId) {
        Board board = new Board();
        board.setId(boardId);
        return ResponseEntity.ok(service.removeBoard(userId, board));
    }

    @PostMapping("/{userId}/events")
    public ResponseEntity<?> addEvent(@PathVariable("userId") String userId,
                                      @Valid @RequestBody Event event) {
        return new ResponseEntity<>(service.addEvent(userId, event), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/events/{eventId}")
    public ResponseEntity<?> removeEvent(@PathVariable("userId") String userId,
                                         @PathVariable("eventId") Long eventId) {
        Event event = new Event();
        event.setId(eventId);
        return ResponseEntity.ok(service.removeEvent(userId, event));
    }

    @Override
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody UserUpdateRequest entity) {
        return ResponseEntity.ok(service.update(entity));
    }

    @Override
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable("userId") String id) {
        User user = new User();
        user.setId(id);
        service.remove(user);
        return ResponseEntity.ok().build();
    }
}
