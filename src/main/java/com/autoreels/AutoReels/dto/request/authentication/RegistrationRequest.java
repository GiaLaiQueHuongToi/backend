package com.autoreels.AutoReels.dto.request.authentication;

import com.autoreels.AutoReels.enums.RoleName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {
    @Size(min = 5, message = "INVALID_USERNAME")
    @NotNull(message = "INVALID_USERNAME")
    String username;

    @Size(min = 8, message = "INVALID_PASSWORD")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&*(){}\\[\\]!~`|])(?=.*\\d).*$", message = "INVALID_PASSWORD")
    String password;

    // Long employeeId;

//    String phoneNumber;
//
//    String email;
//
//    LocalDate dateOfBirth;
//
//    String address;
//
//    String area;
//
//    String ward;

    String notes;

    @NotNull(message = "REQUIRED_ROLE")
    RoleName role;

}
