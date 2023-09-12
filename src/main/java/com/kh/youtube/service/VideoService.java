package com.kh.youtube.service;

import com.kh.youtube.domain.Video;
import com.kh.youtube.domain.VideoComment;
import com.kh.youtube.repository.VideoCommentDAO;
import com.kh.youtube.repository.VideoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoDAO videoDAO;

    public List<Video> showAll(){
        return videoDAO.findAll();
    }

    public Video show(int id){
        return videoDAO.findById(id).orElse(null);
    }

    public Video create(Video vo){
        return videoDAO.save(vo);
    }

    public Video update(Video vo){

        Video target = videoDAO.findById(vo.getVideoCode()).orElse(null);

        if(target != null) return videoDAO.save(vo);

        return null;
    }


    public Video delete(int id){
        Video target = videoDAO.findById(id).orElse(null);

        videoDAO.delete(target);

        return target;
    }

    public List<Video> findByChannelCode(int code){
        return videoDAO.findByChannelCode(code);
    }

}
