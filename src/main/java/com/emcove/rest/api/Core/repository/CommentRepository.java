package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
