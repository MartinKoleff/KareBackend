package com.koleff.kare.auth.mapper;

import com.koleff.kare.auth.models.dto.UserDto;
import com.koleff.kare.auth.models.entity.User;
import com.koleff.kare.exercise.models.dto.ExerciseDetailsDto;
import com.koleff.kare.exercise.models.entity.ExerciseDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
