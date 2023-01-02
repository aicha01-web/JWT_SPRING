package com.groupeisi.repository;

import com.groupeisi.entity.AppRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRoleRepository extends JpaRepository<AppRoleEntity,Long> {
}
