package com.groupeisi.controller;

import com.groupeisi.domain.AddRoleToUser;
import com.groupeisi.domain.AppUser;
import com.groupeisi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Page<AppUser> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AppUser> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/user")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppUser createUser(@Valid @RequestBody AppUser appUser) {
        return userService.createUser(appUser);
    }

    @PutMapping("/users/{id}")
    public AppUser updateAppUser(@PathVariable("id") Long id, @Valid @RequestBody AppUser appUser) {
        return userService.updateUser(id, appUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/addRoleToUser")
    @PostAuthorize("hasAuthority('SUPERADMIN')")
    @ResponseStatus(code = HttpStatus.OK)
    public void addRoleToUser(@Valid @RequestBody AddRoleToUser roleToUser) {
        userService.addRoleToUser(roleToUser.getIdUser(),roleToUser.getIdRole());
    }
    
}
