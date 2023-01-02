package com.groupeisi.controller;

import com.groupeisi.domain.AppRole;
import com.groupeisi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/roles")
    public Page<AppRole> getRoles(Pageable pageable) {
        return roleService.getRoles(pageable);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<AppRole> getrole(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roleService.getRole(id));
    }

    @PostMapping("/role")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppRole createRole(@Valid @RequestBody AppRole approle) {
        return roleService.createRole(approle);
    }

    @PutMapping("/roles/{id}")
    public AppRole updateAppRole(@PathVariable("id") Long id, @Valid @RequestBody AppRole approle) {
        return roleService.updateRole(id, approle);
    }

    @DeleteMapping("/roles/{id}")
    public void deleterole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
    }
}
