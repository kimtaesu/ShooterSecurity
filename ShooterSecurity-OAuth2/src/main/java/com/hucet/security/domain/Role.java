package com.hucet.security.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ROLE")
public class Role implements GrantedAuthority {


    public enum RoleType {
        ADMIN, USER
    }

    @Id
    @GeneratedValue
    @Column(name = "ROLE_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private RoleType roleType;


    @Override
    public String getAuthority() {
        return roleType.name();
    }
}
