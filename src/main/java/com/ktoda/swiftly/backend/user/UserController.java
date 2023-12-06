package com.ktoda.swiftly.backend.user;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Profile("dev")
@AllArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService service;
}
