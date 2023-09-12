package com.kh.youtube.controller;


import com.kh.youtube.domain.CommentLike;
import com.kh.youtube.domain.Video;
import com.kh.youtube.domain.VideoComment;
import com.kh.youtube.domain.VideoLike;
import com.kh.youtube.repository.VideoLikeDAO;
import com.kh.youtube.service.CommentLikeService;
import com.kh.youtube.service.VideoCommentService;
import com.kh.youtube.service.VideoLikeService;
import com.kh.youtube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class VideoController {

    @Autowired
    private VideoService videoService;


    @Autowired
    private VideoCommentService vCommentService;

    @Autowired
    private VideoLikeService vLikeService;

    @Autowired
    private CommentLikeService cLikeService;

    // 영상 전체 조회 : GET - http://localhost:8080/api/video
    @GetMapping("/video")
    public ResponseEntity<List<Video>> showAllVideo(){
        return ResponseEntity.status(HttpStatus.OK).body(videoService.showAll());
    }

    
    // 영상 추가 : POST - http://localhost:8080/api/video
    @PostMapping("/video")
    public ResponseEntity<Video> newVideo(@RequestBody Video vo){
        return ResponseEntity.status(HttpStatus.OK).body(videoService.create(vo));
    }
    
    // 영상 수정 : PUT - http://localhost:8080/api/video
    @PutMapping("/video")
    public ResponseEntity<Video> updateVideo(@RequestBody Video vo){
        return ResponseEntity.status(HttpStatus.OK).body(videoService.update(vo));
    }
    
    // 영상 삭제 : DELETE - http://localhost:8080/api/video/1
    @DeleteMapping("/video/{id}")
    public ResponseEntity<Video> deleteVideo(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(videoService.delete(id));
    }
    
    // 영상 1개 조회 : GET - http://localhost:8080/api/video/1

    @GetMapping("/video/{id}")
    public ResponseEntity<Video> showVideo(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(videoService.show(id));
    }

    
    // 영상 1개에 따른 댓글 전체 조회 : GET - http://localhost:8080/api/video/1/comment

    @GetMapping("/video/{code}/comment")
    public ResponseEntity<List<VideoComment>> vCommentList(@PathVariable int code){
        return ResponseEntity.status(HttpStatus.OK).body(vCommentService.findByVideoCode(code));
    }


    // 좋아요 추가 : POST - http://localhost:8080/api/video/like

    @PostMapping("/video/like")
    public ResponseEntity<VideoLike> createVLike(@RequestBody VideoLike vo){
        return ResponseEntity.status(HttpStatus.OK).body(vLikeService.create(vo));
    }
    
    
    // 좋아요 취소 : DELETE - http://localhost:8080/api/video/like/1
    @DeleteMapping("/video/like/1")
    public ResponseEntity<VideoLike> deleteVLike(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(vLikeService.delete(id));
    }
    
    // 댓글 추가 : POST - http://localhost:8080/api/video/comment
    @PostMapping("/video/comment")
    public ResponseEntity<VideoComment> createComment(@RequestBody VideoComment vo){
        return ResponseEntity.status(HttpStatus.OK).body(vCommentService.create(vo));
    }

    
    // 댓글 수정 : PUT - http://localhost:8080/api/video/comment
    @PutMapping("/video/comment")
    public ResponseEntity<VideoComment> updateComment(@RequestBody VideoComment vo){
        return ResponseEntity.status(HttpStatus.OK).body(vCommentService.update(vo));
    }
    
    
    // 댓글 삭제 : DELETE - http://localhost:8080/api/video/comment/1
    @DeleteMapping("/video/comment/1")
    public ResponseEntity<VideoComment> deleteComment(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(vCommentService.delete(id));
    }

    // 댓글 좋아요 추가 : POST - http://localhost:8080/api/video/comment/like
    @PostMapping("/video/comment/like")
    public ResponseEntity<CommentLike> createCLike(@RequestBody CommentLike vo){
        return ResponseEntity.status(HttpStatus.OK).body(cLikeService.create(vo));
    }

    // 댓글 좋아요 취소 : DELETE - http://localhost:8080/api/video/comment/like/1

    @DeleteMapping("/video/comment/like/{id}")
    public ResponseEntity<CommentLike> deleteCLike(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(cLikeService.delete(id));
    }

    
}
