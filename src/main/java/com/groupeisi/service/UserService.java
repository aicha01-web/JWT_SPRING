package com.groupeisi.service;

import com.groupeisi.domain.AppUser;
import com.groupeisi.entity.AppRoleEntity;
import com.groupeisi.entity.AppUserEntity;
import com.groupeisi.exception.EntityNotFoundException;
import com.groupeisi.exception.RequestException;
import com.groupeisi.mapping.UserMapper;
import com.groupeisi.repository.IAppRoleRepository;
import com.groupeisi.repository.IAppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@AllArgsConstructor
public class UserService {

    IAppUserRepository appUserRepository;

    IAppRoleRepository appRoleRepository;
    UserMapper userMapper;
    MessageSource messageSource;

    @Transactional(readOnly = true)
    public Page<AppUser> getUsers(Pageable pageable) {
        return appUserRepository.findAll(pageable).map(userMapper::toAppUser);
    }

    @Transactional(readOnly = true)
    public AppUser getUser(Long id) {
        return userMapper.toAppUser(appUserRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }

    @Transactional
    public AppUser createUser(AppUser appUser) {
        appUserRepository.findById(appUser.getId())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("user.exists", new Object[]{appUser.getId()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return userMapper.toAppUser(appUserRepository.save(userMapper.fromAppUser(appUser)));
    }

    @Transactional
    public AppUser updateUser(Long id, AppUser appUser){
        return appUserRepository.findById(id)
                .map(entity -> {
                    appUser.setId(id);
                    return userMapper.toAppUser(appUserRepository.save(userMapper.fromAppUser(appUser)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound",
                        new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteUser(Long id) {
        try {
            appUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("user.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }

    @Transactional
    public void addRoleToUser(Long idUser,Long idRole){
        AppUserEntity appUser = appUserRepository.findById(idUser).get();
        AppRoleEntity appRoles = appRoleRepository.findById(idRole).get();
        appUser.getRoles().add(appRoles);
    }
}
