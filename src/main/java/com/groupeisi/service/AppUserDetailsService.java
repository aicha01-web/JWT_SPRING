package com.groupeisi.service;

import com.groupeisi.entity.AppUserEntity;
import com.groupeisi.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    IAppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUserEntity appUser = appUserRepository.findByUsername(username);

        return new User(appUser.getUsername(), appUser.getPassword(), new ArrayList<>());
    }
}
