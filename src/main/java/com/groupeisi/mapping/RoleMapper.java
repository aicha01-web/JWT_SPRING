package com.groupeisi.mapping;

import com.groupeisi.domain.AppRole;
import com.groupeisi.entity.AppRoleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    AppRole toAppRole(AppRoleEntity appRoleEntity);
    AppRoleEntity fromAppRole(AppRole appRole);
}
