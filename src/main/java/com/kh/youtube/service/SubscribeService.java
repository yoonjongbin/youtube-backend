package com.kh.youtube.service;


import com.kh.youtube.domain.CommentLike;
import com.kh.youtube.domain.Subscribe;
import com.kh.youtube.repository.CommentLikeDAO;
import com.kh.youtube.repository.SubscribeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribeService {

    @Autowired
    private SubscribeDAO subscribeDAO;

    public List<Subscribe> showAll(){
        return subscribeDAO.findAll();
    }

    public Subscribe show(int id){
        return subscribeDAO.findById(id).orElse(null);
    }

    public Subscribe create(Subscribe vo){
        return subscribeDAO.save(vo);
    }

    public Subscribe update(Subscribe vo){

        Subscribe target = subscribeDAO.findById(vo.getSubsCode()).orElse(null);

        if(target != null) return subscribeDAO.save(vo);

        return null;
    }


    public Subscribe delete(int id){
        Subscribe target = subscribeDAO.findById(id).orElse(null);

        subscribeDAO.delete(target);

        return target;
    }

    public List<Subscribe> findByMemberId(String id){
        return subscribeDAO.findByMemberId(id);
    }
}
