package com.ktoda.swiftly.backend.password;

import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {
    @Override
    public String encodePassword(String password) {
        return password;
    }
}
