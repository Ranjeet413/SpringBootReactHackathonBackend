package com.ranjeet.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranjeet.blog.entities.Comment;

public interface CommentRepo  extends JpaRepository<Comment	, Integer> {

}