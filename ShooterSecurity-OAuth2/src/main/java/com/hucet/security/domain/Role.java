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
    @Id
    @GeneratedValue
    @Column(name = "ROLE_ID")
    private Long id;

    @Column(length = 15, nullable = false)
    private String roleType;


    @Override
    public String getAuthority() {
        return roleType;
    }
}
