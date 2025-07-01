package com.autoreels.AutoReels.repository;

import com.autoreels.AutoReels.entity.Role;
import com.autoreels.AutoReels.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, RoleName> {

}
