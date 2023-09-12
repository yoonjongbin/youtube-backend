package com.kh.youtube.service;

import com.kh.youtube.domain.CommentLike;
import com.kh.youtube.repository.CommentLikeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentLikeService {

    @Autowired
    private CommentLikeDAO commentLikeDAO;

    public List<CommentLike> showAll(){
        return commentLikeDAO.findAll();
    }

    public CommentLike show(int id){
        return commentLikeDAO.findById(id).orElse(null);
    }

    public CommentLike create(CommentLike vo){
        return commentLikeDAO.save(vo);
    }

    public CommentLike update(CommentLike vo){

        CommentLike target = commentLikeDAO.findById(vo.getCommLikeCode()).orElse(null);

        if(target != null) return commentLikeDAO.save(vo);

        return null;
    }


    public CommentLike delete(int id){
        CommentLike target = commentLikeDAO.findById(id).orElse(null);

        commentLikeDAO.delete(target);

        return target;
    }
}
