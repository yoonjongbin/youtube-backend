package com.kh.youtube.repository;

import com.kh.youtube.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeDAO extends JpaRepository<Subscribe, Integer> {
    
    // 내가 구독한 채널 조회
    // SELECT * FROM SUBCRIBE WHERE ID = ?
    @Query(value = "SELECT * FROM SUBCRIBE WHERE ID = :id", nativeQuery = true)
    List<Subscribe> findByMemberId(@Param("id") String id);
}
