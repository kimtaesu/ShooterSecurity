package com.hucet.security.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 계정 관련 설정들
 *
 * @see <a href="http://zgundam.tistory.com/49">http://zgundam.tistory.com/49</a>
 * <p>
 * <p>
 * isAccountNonExpired() 계정이 만료되지 않았는지 리턴
 * isAccountNonLocked() 계정이 잠겨있지 않은지 리턴
 * isCredentialsNonExpired() 계정의 패스워드가 만료되지 않았는지 리턴
 * isEnabled() 계정이 사용가능한 계정인지 리턴
 * getAuthorities() 계정이 갖고 있는 권한 목록 리턴
 */

@Entity
@Getter
@Setter
@Table(name = "OAUTH")
public class OAuthAccount {
    @Id
    @GeneratedValue
    @Column(name = "OAUTH_ID")
    private Long id;

    @Column(unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    private boolean isEnabled;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    @ManyToMany
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();
}
