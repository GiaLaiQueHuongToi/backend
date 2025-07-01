package com.autoreels.AutoReels.configuration;

import com.autoreels.AutoReels.entity.Role;
import com.autoreels.AutoReels.entity.User;
import com.autoreels.AutoReels.enums.RoleName;
import com.autoreels.AutoReels.repository.RoleRepository;
import com.autoreels.AutoReels.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    UserRepository userRepository;
    RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${app.init-admin-account.username}")
    String ADMIN_USERNAME;

    @NonFinal
    @Value("${app.init-admin-account.password}")
    String ADMIN_PASSWORD;

    @NonFinal
    @Value("${app.role.admin.description}")
    String ADMIN_DESCRIPTION;

    @NonFinal
    @Value("${app.role.user.description}")
    String USER_DESCRIPTION;

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "org.postgresql.Driver")
    public ApplicationRunner applicationRunner() {
        return args -> {
            if (!roleRepository.existsById(RoleName.ADMIN)) {
                Role adminRole = Role.builder()
                        .name(RoleName.ADMIN)
                        .description(ADMIN_DESCRIPTION)
                        .build();
                roleRepository.save(adminRole);
                User admin = User.builder()
                        .username(ADMIN_USERNAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .role(adminRole)
                        .build();
                userRepository.save(admin);
            }
            if (!roleRepository.existsById(RoleName.USER)) {
                Role userRole = Role.builder()
                        .name(RoleName.USER)
                        .description(USER_DESCRIPTION)
                        .build();
                roleRepository.save(userRole);
            }
        };
    }
}
