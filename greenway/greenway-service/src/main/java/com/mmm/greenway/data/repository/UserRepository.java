package com.mmm.greenway.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mmm.greenway.entity.User;
import com.mmm.greenway.entity.UserRole;

public interface UserRepository extends CrudRepository<User, String> {
	@Query("select u from User u where upper(u.userName) like :token%")
	List<User> findByUserNameStartsWith(@Param("token") String token);
	
	@Query("select u from User u where :userRole member of u.roles")
	List<User> findUsersWithRole(@Param("userRole") UserRole userRole);
}
