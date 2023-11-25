package com.ktoda.swiftly.backend.user;

import com.ktoda.swiftly.backend.common.service.Service;
import com.ktoda.swiftly.backend.user.dtos.UserCreateRequest;
import com.ktoda.swiftly.backend.user.dtos.UserUpdateRequest;
import com.ktoda.swiftly.backend.user.User;

public interface UserService extends Service<User, String> {
    User findByEmail(String email);

    User create(UserCreateRequest createRequest);

    User update(UserUpdateRequest updateRequest);

    // TODO: addBoard, remove, addEvent, remove

}
