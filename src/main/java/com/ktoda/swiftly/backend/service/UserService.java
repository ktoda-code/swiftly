package com.ktoda.swiftly.backend.service;

import com.ktoda.swiftly.backend.dto.user.UserCreateRequest;
import com.ktoda.swiftly.backend.model.user.User;

public interface UserService extends Service<User, String> {
    User findByEmail(String email);

    User create(UserCreateRequest createRequest);
}
