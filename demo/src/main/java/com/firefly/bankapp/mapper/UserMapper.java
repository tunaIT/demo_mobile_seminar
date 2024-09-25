package com.firefly.bankapp.mapper;

import com.firefly.bankapp.dto.GetUserInfoDto;
import com.firefly.bankapp.entity.UserEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    GetUserInfoDto entityToDto(UserEntity userEntity);

    UserEntity dtoToEntity(GetUserInfoDto getUserInfoDto);
}
