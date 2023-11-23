package com.ktoda.swiftly.backend.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        @Email(message = "Must be a valid email")
        String email,
        @NotNull
        @Size(min = 8, max = 18)
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[A-Z]).{8,18}$",
                message = "Password must have 1 upper case, 1 symbol, and 1 number at least, " +
                        "and be at least 8 to 18 characters long")
        String password
) {
}
