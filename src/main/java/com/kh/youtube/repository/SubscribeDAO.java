package com.kh.youtube.repository;

import com.kh.youtube.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeDAO extends JpaRepository<Subscribe, Integer> {
}
