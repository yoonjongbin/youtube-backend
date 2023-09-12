package com.kh.youtube.repository;


import com.kh.youtube.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeDAO extends JpaRepository<CommentLike, Integer> {


}
