package com.autoreels.AutoReels.mapper;

import com.autoreels.AutoReels.dto.request.authentication.RegistrationRequest;
import com.autoreels.AutoReels.dto.request.authentication.UserUpdateRequest;
import com.autoreels.AutoReels.dto.response.UserResponse;
import com.autoreels.AutoReels.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    // specify which fields of the target object should be ignored
    @Mapping(target = "role", ignore = true)
    // @Mapping(target = "employee", ignore = true)
    User toUser(RegistrationRequest request);

    @Mapping(target = "role", ignore = true)
    // @Mapping(target = "employee", ignore = true)
    UserResponse toUserResponse(User user);

    @Mapping(target = "role", ignore = true)
    // @Mapping(target = "employee", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
