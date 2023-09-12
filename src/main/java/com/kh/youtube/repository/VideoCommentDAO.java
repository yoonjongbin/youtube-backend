package com.kh.youtube.repository;

import com.kh.youtube.domain.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoCommentDAO extends JpaRepository<VideoComment, Integer> {
}
