package com.mohanjp.store.mapper;

import com.mohanjp.store.dto.RegisterUserRequest;
import com.mohanjp.store.dto.UserDto;
import com.mohanjp.store.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity user);

    UserEntity toEntity(RegisterUserRequest request);
}
