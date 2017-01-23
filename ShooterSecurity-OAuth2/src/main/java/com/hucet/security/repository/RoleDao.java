package com.hucet.security.repository;


import com.hucet.security.domain.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface RoleDao extends Repository<Role, Long> {
	Optional<Role> findByRoleType(String roleType);

	Role save(Role role);
}
