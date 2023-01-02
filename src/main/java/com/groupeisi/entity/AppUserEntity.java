package com.groupeisi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name="utilisateurs_roles")
    private List<AppRoleEntity> roles;


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AppRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<AppRoleEntity> roles) {
        this.roles = roles;
    }


}
