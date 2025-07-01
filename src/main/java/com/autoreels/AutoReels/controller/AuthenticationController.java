package com.autoreels.AutoReels.controller;

import com.autoreels.AutoReels.dto.request.authentication.*;
import com.autoreels.AutoReels.dto.response.ApiResponse;
import com.autoreels.AutoReels.dto.response.LoginResponse;
import com.autoreels.AutoReels.dto.response.UserResponse;
import com.autoreels.AutoReels.entity.User;
import com.autoreels.AutoReels.service.authentication.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableMethodSecurity
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Authentication Controller", description = "APIs for managing user authentication")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @NonFinal
    @Value("${app.controller.authentication.response.logout.success}")
    String LOGOUT_SUCCESS_MESSAGE;

    @NonFinal
    @Value("${app.controller.authentication.response.change-password.success}")
    String CHANGE_PASSWORD_SUCCESS_MESSAGE;

    @NonFinal
    @Value("${app.controller.user.response.delete.success}")
    String DELETE_SUCCESS;



    @PostMapping("/login")
    @Operation(summary = "Login user",
            description = "Login user with username and password and get access token")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ApiResponse.<LoginResponse>builder()
                .data(authenticationService.login(loginRequest))
                .build();
    }

    @PostMapping("/register")
    @Operation(summary = "Register user",
            description = "Register user")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<UserResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return ApiResponse.<UserResponse>builder()
                .data(authenticationService.register(registrationRequest))
                .build();
    }

    @GetMapping()
    @Operation(summary = "Get information of my account",
            description = "Get information of my account")
    public ApiResponse<UserResponse> getCurrentUser() {
        return ApiResponse.<UserResponse>builder()
                .data(authenticationService.getCurrentUser())
                .build();
    }

    @GetMapping("/all")
    @Operation(summary = "Get all users",
            description = "Get all users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .data(authenticationService.getAllUsers())
                .build();
    }

    @PatchMapping("/change-password")
    @Operation(summary = "Change password",
            description = "Change password of user with old password and new password")
    public ApiResponse<Void> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        authenticationService.changePassword(changePasswordRequest);
        return ApiResponse.<Void>builder().message(CHANGE_PASSWORD_SUCCESS_MESSAGE).build();
    }

    @DeleteMapping()
    @Operation(summary = "Delete a user",
            description = "Delete a user by providing the user ID")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApiResponse<Void> deleteUser(@RequestParam("id") Long id) {
        authenticationService.deleteUser(id);
        return ApiResponse.<Void>builder()
                .message(DELETE_SUCCESS)
                .build();
    }

    @PutMapping()
    @Operation(summary = "Update user",
            description = "Update user")
    public ApiResponse<UserResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        return ApiResponse.<UserResponse>builder()
                .data(authenticationService.updateUser(userUpdateRequest))
                .build();
    }


    @GetMapping("/grantcode")
    public void grantCode(@RequestParam("code") String code, @RequestParam("scope") String scope) {
        System.out.println("Authorization code: " + code);
        System.out.println("Scope: " + scope);
        authenticationService.getOauthAccessTokenGoogle(code);
    }

    @GetMapping("/access-token")
    public ApiResponse<Map<String, String>> getAccessToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        String accessToken = authenticationService.getAccessToken(user.getId());

        Map<String, String> response = new HashMap<>();
        response.put("accessToken", accessToken);

        return ApiResponse.<Map<String, String>>builder()
                .data(response)
                .build();
    }

    @PostMapping("/store-token")
    @Operation(summary = "Store OAuth tokens",
            description = "Store OAuth tokens for the current authenticated user")
    public ApiResponse<Void> storeTokens(@Valid @RequestBody TokenStoreRequest tokenStoreRequest) {
        authenticationService.storeOAuthTokens(tokenStoreRequest);
        return ApiResponse.<Void>builder()
                .message("Tokens stored successfully")
                .code(1000)
                .build();
    }

    @GetMapping("/youtube-access-token")
    @Operation(summary = "Get YouTube access token",
            description = "Retrieve the stored access token or refresh if expired")
    public ApiResponse<Map<String, String>> getYoutubeAccessToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        String accessToken = authenticationService.getAccessToken(user.getId());

        Map<String, String> response = new HashMap<>();
        response.put("accessToken", accessToken);

        return ApiResponse.<Map<String, String>>builder()
                .data(response)
                .code(1000)
                .build();
    }


}
