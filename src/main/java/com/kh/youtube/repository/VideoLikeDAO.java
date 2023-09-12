package com.kh.youtube.repository;

import com.kh.youtube.domain.VideoLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoLikeDAO extends JpaRepository<VideoLike, Integer> {
}
