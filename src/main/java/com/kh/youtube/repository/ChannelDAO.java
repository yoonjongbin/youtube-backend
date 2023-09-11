package com.kh.youtube.repository;

import com.kh.youtube.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChannelDAO extends JpaRepository<Channel, Integer> {
    
    // 특정 멤버의 모든 채널 조회
    // 우선 쿼리문을 어떻게 쓸건지 생각하자(SELECT * FROM CHANNEL WHERE MEMBER_ID = ?)
    // value에는 쿼리문이 들어가며 메소드가 받은 memberId 매개변수 앞에 ':' 붙이는 방식으로 넣어준다.
    @Query(value="SELECT * FROM CHANNEL WHERE ID = :memberId", nativeQuery = true)
    List<Channel> findByMemberId(@Param("memberId") String memberId);
}
