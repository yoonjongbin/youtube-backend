package com.kh.youtube.repository;

import com.kh.youtube.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoDAO extends JpaRepository<Video, Integer> {
}
