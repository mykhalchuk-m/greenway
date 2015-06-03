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

	@Query("select u from User u where u.role = :userRole")
	List<User> findUsersWithRole(@Param("userRole") UserRole userRole);

	@Query("select u from User u where u.role = :userRole and upper(u.userName) like :token%")
	List<User> findUsersWithRoleAndNameStartsWith(@Param("userRole") UserRole userRole, @Param("token") String token);
	
	List<String> findAllLocations();
}
