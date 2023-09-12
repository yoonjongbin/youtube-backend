package com.kh.youtube.repository;

import com.kh.youtube.domain.VideoComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoCommentDAO extends JpaRepository<VideoComment, Integer> {

    // 영상 1개에 따른 댓글 전체 조회
    // SELECT * FROM VIDEO_COMMENT WHERE VIDEO_CODE = ?
    @Query(value = "SELECT * FROM VIDEO_COMMENT WHERE VIDEO_CODE = :vCode", nativeQuery = true)
    List<VideoComment> findByVideoCode(@Param("vCode") int vCode);
}
