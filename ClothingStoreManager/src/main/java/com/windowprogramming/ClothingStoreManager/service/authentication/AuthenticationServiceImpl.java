package com.windowprogramming.ClothingStoreManager.service.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windowprogramming.ClothingStoreManager.dto.request.authentication.*;
import com.windowprogramming.ClothingStoreManager.dto.response.LoginResponse;
import com.windowprogramming.ClothingStoreManager.dto.response.UserResponse;
import com.windowprogramming.ClothingStoreManager.entity.Employee;
import com.windowprogramming.ClothingStoreManager.entity.Role;
import com.windowprogramming.ClothingStoreManager.entity.User;
import com.windowprogramming.ClothingStoreManager.enums.RoleName;
import com.windowprogramming.ClothingStoreManager.exception.AppException;
import com.windowprogramming.ClothingStoreManager.exception.ErrorCode;
import com.windowprogramming.ClothingStoreManager.mapper.EmployeeMapper;
import com.windowprogramming.ClothingStoreManager.mapper.RoleMapper;
import com.windowprogramming.ClothingStoreManager.mapper.UserMapper;
import com.windowprogramming.ClothingStoreManager.repository.EmployeeRepository;
import com.windowprogramming.ClothingStoreManager.repository.RoleRepository;
import com.windowprogramming.ClothingStoreManager.repository.UserRepository;
import com.windowprogramming.ClothingStoreManager.utils.JWTUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    @NonFinal
    @Value("${google.client.id}")
    private String clientId;

    @NonFinal
    @Value("${google.client.secret}")
    private String clientSecret;

    UserRepository userRepository;
    RoleRepository roleRepository;
    EmployeeRepository employeeRepository;

    PasswordEncoder passwordEncoder;
    JWTUtils jwtUtils;
    UserMapper userMapper;
    RoleMapper roleMapper;
    EmployeeMapper employeeMapper;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_USERNAME_PASSWORD));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_USERNAME_PASSWORD);
        }

        return buildLoginResponse(user);
    }

    private LoginResponse buildLoginResponse(User user) {
        return LoginResponse.builder()
                .token(jwtUtils.generateToken(user))
                .build();
    }

    private UserResponse buildUserResponse(User user){
        UserResponse userResponse = userMapper.toUserResponse(user);
        userResponse.setRole(roleMapper.toRoleResponse(user.getRole()));
        userResponse.setEmployee((user.getEmployee() != null) ? employeeMapper.toEmployeeResponse(user.getEmployee()) : null);
        return userResponse;
    }

    @Override
    public UserResponse register(RegistrationRequest registrationRequest) {
        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(registrationRequest);

        Role role = roleRepository.findById(registrationRequest.getRole())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        user.setRole(role);

        Employee employee = null;
        if(registrationRequest.getRole() == RoleName.USER) {
            if(registrationRequest.getEmployeeId() == null) {
                throw new AppException(ErrorCode.REQUIRED_EMPLOYEE_ID);
            }
            else {
                employee = employeeRepository.findById(registrationRequest.getEmployeeId())
                        .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
                if(userRepository.existsByEmployee(employee)) {
                    throw new AppException(ErrorCode.EMPLOYEE_ALREADY_HAS_USER);
                }
            }
        }
        user.setEmployee(employee);
        updateUserBasedOnEmployee(user, employee);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);

        return buildUserResponse(user);
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        // do not need to implement logout
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INCORRECT_PASSWORD);
        }

        if (request.getOldPassword().equals(request.getNewPassword())) {
            throw new AppException(ErrorCode.SAME_PASSWORD);
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if(id == 1) {
            throw new AppException(ErrorCode.CANNOT_DELETE_ROOT_ADMIN);
        }
        if(userRepository.findById(id).isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currUser = (User) authentication.getPrincipal();
        Long userId = currUser.getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return buildUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UserUpdateRequest userUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currUser = (User) authentication.getPrincipal();
        Long userId = currUser.getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, userUpdateRequest);

        Employee employee = user.getEmployee();
        if(employee != null) {
            updateEmployeeBasedOnUser(employee, user);
            employeeRepository.save(employee);
        }

        userRepository.save(user);
        return buildUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for(User user : users) {
            userResponses.add(buildUserResponse(user));
        }
        return userResponses;
    }

    @Override
    public String getOauthAccessTokenGoogle(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("redirect_uri", "http://localhost:8080/auth/grantcode");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("scope", "openid");
        params.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, httpHeaders);

        String url = "https://oauth2.googleapis.com/token";
        String response = restTemplate.postForObject(url, requestEntity, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);

            if (rootNode.has("access_token")) {
                String accessToken = rootNode.get("access_token").asText();
                System.out.println("Access Token: " + accessToken);
            } else {
                System.out.println("Access token not found in response: " + response);
            }
        } catch (Exception e) {
            System.err.println("Error parsing OAuth response: " + e.getMessage());
        }

        return response;
    }

    private void updateUserBasedOnEmployee(User user, Employee employee) {
        user.setPhoneNumber(employee.getPhoneNumber());
        user.setEmail(employee.getEmail());
        user.setDateOfBirth(employee.getDateOfBirth());
        user.setAddress(employee.getAddress());
        user.setArea(employee.getArea());
        user.setWard(employee.getWard());
    }

    private void updateEmployeeBasedOnUser(Employee employee, User user) {
        employee.setPhoneNumber(user.getPhoneNumber());
        employee.setEmail(user.getEmail());
        employee.setDateOfBirth(user.getDateOfBirth());
        employee.setAddress(user.getAddress());
        employee.setArea(user.getArea());
        employee.setWard(user.getWard());
    }
}
