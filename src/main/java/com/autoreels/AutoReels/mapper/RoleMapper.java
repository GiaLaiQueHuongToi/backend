package com.autoreels.AutoReels.mapper;

import com.autoreels.AutoReels.dto.response.RoleResponse;
import com.autoreels.AutoReels.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {
//    @Mapping(target = "permissions", ignore = true)
//    Role toRole(RoleCreationRequest request);

//    @Mapping(target = "permissions", ignore = true)
//    void updateRole(@MappingTarget Role role, RoleUpdateRequest request);

    RoleResponse toRoleResponse(Role role);
}
