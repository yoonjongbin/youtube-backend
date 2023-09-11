package com.kh.youtube.service;

import com.kh.youtube.domain.Member;
import com.kh.youtube.repository.MemberDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j // log 출력(lombok에서 제공)
@Service
public class MemberService {

    @Autowired
    private MemberDAO dao;

    public List<Member> showAll(){
        return dao.findAll(); // SELECT * FROM MEMBER;
    }

    public Member show(String id){
        return dao.findById(id).orElse(null); // SELECT * FROM MEMBER WHERE ID = ?
    }

    public Member create(Member member){
        // INSERT INTO MEMBER(id, password, name, authority) VALUES(?, ?, ?, 'ROLE_USER') default값 알아서 들어감

        return dao.save(member);
    }


    public Member update(Member member){
        // UPDATE MEMBER SET ID = ?, PASSWORD = ? , NAME = ? , AUTHORITY = ? WHERE ID = ?
        Member target = dao.findById(member.getId()).orElse(null);
        if(target!=null){ // 기존 회원인 경우에만 수정, 아닌 경우에는 null로 리턴
            return dao.save(member); 
        }
        return null;
    }

    public Member delete(String id){
        // DELETE FROM MEMBER WHERE ID=?
        Member target = dao.findById(id).orElse(null);
        dao.delete(target);
        return target;
    }


}
