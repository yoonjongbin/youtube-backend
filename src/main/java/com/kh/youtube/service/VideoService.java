package com.kh.youtube.service;

import com.kh.youtube.domain.Video;
import com.kh.youtube.domain.VideoComment;
import com.kh.youtube.repository.VideoCommentDAO;
import com.kh.youtube.repository.VideoDAO;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoDAO videoDAO;

    public Page<Video> showAll(Pageable pageable, BooleanBuilder builder){

        // dao.findAll() -> List<Video>
        // dao.findAll(pageable) -> Page<Video>

        // builder를 먼저 넘겨줘야 한다.
        return videoDAO.findAll(builder, pageable);
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
