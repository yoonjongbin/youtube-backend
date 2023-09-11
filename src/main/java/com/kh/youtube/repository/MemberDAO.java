package com.kh.youtube.repository;

import com.kh.youtube.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDAO extends JpaRepository<Member, String> { // primary key의 데이터 타입


}
