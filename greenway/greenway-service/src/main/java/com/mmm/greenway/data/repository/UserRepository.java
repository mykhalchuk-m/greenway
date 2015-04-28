package com.mmm.greenway.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.mmm.greenway.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

}
