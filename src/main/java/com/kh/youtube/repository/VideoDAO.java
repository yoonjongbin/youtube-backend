package com.kh.youtube.repository;

import com.kh.youtube.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoDAO extends JpaRepository<Video, Integer> {
    
    // 채널별 목록보기
    @Query(value = "SELECT * FROM VIDEO WHERE CHANNEL_CODE = :code", nativeQuery = true)
    List<Video> findByChannelCode(@Param("code") int code);
}
