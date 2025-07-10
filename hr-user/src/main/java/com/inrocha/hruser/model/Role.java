package com.inrocha.hruser.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;

    public Role() {
    }

    public Role(Long id, String roleName) {
        super();
        this.id = id;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Role role)) return false;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}