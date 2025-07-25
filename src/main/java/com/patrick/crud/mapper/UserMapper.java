package com.patrick.crud.mapper;

import com.patrick.crud.entity.User;
import com.patrick.crud.models.requests.CreateUserRequest;
import com.patrick.crud.models.requests.UpdateUserRequest;
import com.patrick.crud.models.responses.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
            nullValuePropertyMappingStrategy = IGNORE,
            nullValueCheckStrategy = ALWAYS
)
public interface UserMapper {
    UserResponse fromEntity(final User entity);

    @Mapping(target = "publicId", ignore = true)
    @Mapping(target = "id", ignore = true)
    User fromRequest(CreateUserRequest createUserRequest);

    @Mapping(target = "publicId", ignore = true)
    @Mapping(target = "id", ignore = true)
    User update(UpdateUserRequest updateUserRequest, @MappingTarget User entity);
}
