package com.svaha.admin.user;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.svaha.common.entity.Role;
import com.svaha.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		//test create new user
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userTiger = new User("tiger@boy.net","tiger1234", "Tiger","Sports");
		userTiger.addRole(roleAdmin);
		
		User savedUser= repo.save(userTiger);
		assertThat(savedUser.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		
		User userWill = new User("will53@gmail.com", "will1234", "Will","Yellow");
		
		Role roleEditor = new Role(3);
		Role roleAssistance = new Role(5);
		
		userWill.addRole(roleAssistance);
		userWill.addRole(roleEditor);
		
		User savedUser = repo.save(userWill);
		
		assertThat(savedUser.getId()).isGreaterThan(0);	
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		User userTiger = repo.findById(1).get();
		System.out.println(userTiger);
		assertThat(userTiger).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User userTiger = repo.findById(1).get();
		userTiger.setEnabled(true);
		userTiger.setEmail("tiger@gmail.com");
		
		repo.save(userTiger);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User userWill = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);
		
		userWill.getRoles().remove(roleEditor);
		userWill.addRole(roleSalesperson);
		
		repo.save(userWill);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId=2;
		repo.deleteById(userId);
	}
	
//	@Test
//	public void testGetUserByEmail() {
//		String email = "tiger@gmail.com";
//		User user = repo.getUserByEmail(email);
//		
//		assertThat(user).isNotNull();
//	}

}
