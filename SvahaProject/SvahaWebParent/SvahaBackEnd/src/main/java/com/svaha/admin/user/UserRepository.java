package com.svaha.admin.user;

import org.springframework.data.repository.CrudRepository;

import com.svaha.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	

}
