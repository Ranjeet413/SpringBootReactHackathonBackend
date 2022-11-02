package com.ranjeet.blog.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ranjeet.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}