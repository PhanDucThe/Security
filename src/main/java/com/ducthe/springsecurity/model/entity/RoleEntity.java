package com.ducthe.springsecurity.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
public class RoleEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY)
    private List<UserRoleEntity> userRoleEntityList = new ArrayList<>();

}
