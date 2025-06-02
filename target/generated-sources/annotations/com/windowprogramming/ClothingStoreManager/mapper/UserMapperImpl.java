package com.windowprogramming.ClothingStoreManager.mapper;

import com.windowprogramming.ClothingStoreManager.dto.request.authentication.RegistrationRequest;
import com.windowprogramming.ClothingStoreManager.dto.request.authentication.UserUpdateRequest;
import com.windowprogramming.ClothingStoreManager.dto.response.UserResponse;
import com.windowprogramming.ClothingStoreManager.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Ubuntu)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(RegistrationRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.getUsername() );
        user.password( request.getPassword() );
        user.notes( request.getNotes() );

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.phoneNumber( user.getPhoneNumber() );
        userResponse.email( user.getEmail() );
        userResponse.dateOfBirth( user.getDateOfBirth() );
        userResponse.address( user.getAddress() );
        userResponse.area( user.getArea() );
        userResponse.ward( user.getWard() );
        userResponse.notes( user.getNotes() );

        return userResponse.build();
    }

    @Override
    public void updateUser(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        if ( request.getPhoneNumber() != null ) {
            user.setPhoneNumber( request.getPhoneNumber() );
        }
        if ( request.getEmail() != null ) {
            user.setEmail( request.getEmail() );
        }
        if ( request.getDateOfBirth() != null ) {
            user.setDateOfBirth( request.getDateOfBirth() );
        }
        if ( request.getAddress() != null ) {
            user.setAddress( request.getAddress() );
        }
        if ( request.getArea() != null ) {
            user.setArea( request.getArea() );
        }
        if ( request.getWard() != null ) {
            user.setWard( request.getWard() );
        }
        if ( request.getNotes() != null ) {
            user.setNotes( request.getNotes() );
        }
    }
}
