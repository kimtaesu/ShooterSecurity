package com.hucet.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ACCOUNT", uniqueConstraints = @UniqueConstraint(columnNames = {"userEmail", "userName"}))
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    private String userEmail;

    private String userName;

    private String password;
}

