package com.mohanjp.store.user.mapper;

import com.mohanjp.store.user.dto.RegisterUserRequest;
import com.mohanjp.store.user.dto.UpdateUserRequest;
import com.mohanjp.store.user.dto.UserDto;
import com.mohanjp.store.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity user);

    UserEntity toEntity(RegisterUserRequest request);

    void update(UpdateUserRequest request, @MappingTarget UserEntity user);
}
