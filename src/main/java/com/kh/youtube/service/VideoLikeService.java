package com.kh.youtube.service;


import com.kh.youtube.domain.Video;
import com.kh.youtube.domain.VideoLike;
import com.kh.youtube.repository.VideoDAO;
import com.kh.youtube.repository.VideoLikeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoLikeService {

    @Autowired
    private VideoLikeDAO videoLikeDAO;

    public List<VideoLike> showAll(){
        return videoLikeDAO.findAll();
    }

    public VideoLike show(int id){
        return videoLikeDAO.findById(id).orElse(null);
    }

    public VideoLike create(VideoLike vo){
        return videoLikeDAO.save(vo);
    }

    public VideoLike update(VideoLike vo){

        VideoLike target = videoLikeDAO.findById(vo.getVLikeCode()).orElse(null);

        if(target != null) return videoLikeDAO.save(vo);

        return null;
    }


    public VideoLike delete(int id){
        VideoLike target = videoLikeDAO.findById(id).orElse(null);

        videoLikeDAO.delete(target);

        return target;
    }

}
