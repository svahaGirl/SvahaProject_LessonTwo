package com.svaha.admin.user;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.svaha.common.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{
	
}
