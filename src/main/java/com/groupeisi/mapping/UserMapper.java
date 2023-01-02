package com.groupeisi.mapping;

import com.groupeisi.domain.AppUser;
import com.groupeisi.entity.AppUserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    AppUser toAppUser(AppUserEntity appUserEntity);
    AppUserEntity fromAppUser(AppUser appUser);
}
