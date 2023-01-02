package com.groupeisi.service;

import com.groupeisi.domain.AppRole;
import com.groupeisi.exception.EntityNotFoundException;
import com.groupeisi.exception.RequestException;
import com.groupeisi.mapping.RoleMapper;
import com.groupeisi.repository.IAppRoleRepository;
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
public class RoleService {

    IAppRoleRepository appRoleRepository;
    RoleMapper roleMapper;
    MessageSource messageSource;

    @Transactional(readOnly = true)
    public Page<AppRole> getRoles(Pageable pageable) {
        return appRoleRepository.findAll(pageable).map(roleMapper::toAppRole);
    }

    @Transactional(readOnly = true)
    public AppRole getRole(Long id) {
        return roleMapper.toAppRole(appRoleRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("role.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }

    @Transactional
    public AppRole createRole(AppRole appRole) {
        appRoleRepository.findById(appRole.getId())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("role.exists", new Object[]{appRole.getId()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return roleMapper.toAppRole(appRoleRepository.save(roleMapper.fromAppRole(appRole)));
    }

    @Transactional
    public AppRole updateRole(Long id, AppRole appRole){
        return appRoleRepository.findById(id)
                .map(entity -> {
                    appRole.setId(id);
                    return roleMapper.toAppRole(appRoleRepository.save(roleMapper.fromAppRole(appRole)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("role.notfound",
                        new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteRole(Long id) {
        try {
            appRoleRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("role.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
