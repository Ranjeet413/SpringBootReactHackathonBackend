package com.ranjeet.blog.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ranjeet.blog.entities.Role;

public interface RoleRepo  extends JpaRepository<Role, Integer>{

}