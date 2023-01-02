package com.groupeisi.repository;

import com.groupeisi.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUserEntity,Long> {
    AppUserEntity findByUsername(String username);
}
