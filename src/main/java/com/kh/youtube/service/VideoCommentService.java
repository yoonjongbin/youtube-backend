package com.kh.youtube.service;

import com.kh.youtube.domain.Subscribe;
import com.kh.youtube.domain.VideoComment;
import com.kh.youtube.repository.SubscribeDAO;
import com.kh.youtube.repository.VideoCommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoCommentService {

    @Autowired
    private VideoCommentDAO videoCommentDAO;

    public List<VideoComment> showAll(){
        return videoCommentDAO.findAll();
    }

    public VideoComment show(int id){
        return videoCommentDAO.findById(id).orElse(null);
    }

    public VideoComment create(VideoComment vo){
        return videoCommentDAO.save(vo);
    }

    public VideoComment update(VideoComment vo){

        VideoComment target = videoCommentDAO.findById(vo.getCommentCode()).orElse(null);

        if(target != null) return videoCommentDAO.save(vo);

        return null;
    }


    public VideoComment delete(int id){
        VideoComment target = videoCommentDAO.findById(id).orElse(null);

        videoCommentDAO.delete(target);

        return target;
    }

    public List<VideoComment> findByVideoCode(int code){
        return videoCommentDAO.findByVideoCode(code);
    }
}
