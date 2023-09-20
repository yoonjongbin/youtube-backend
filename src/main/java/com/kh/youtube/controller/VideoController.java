package com.kh.youtube.controller;


import com.kh.youtube.domain.*;
import com.kh.youtube.service.CommentLikeService;
import com.kh.youtube.service.VideoCommentService;
import com.kh.youtube.service.VideoLikeService;
import com.kh.youtube.service.VideoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/*")
@Log4j2
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class VideoController {


    // application.properties에 있는 변수
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

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
    public ResponseEntity<Video> newVideo(MultipartFile video, MultipartFile image, String title, String desc, String categoryCode){
        log.info("video : " + video);
        log.info("image : " + image);
        log.info("title : " + title);
        log.info("desc : " + desc);
        log.info("categoryCode : " + categoryCode);


        // video_title, video_desc, video_url, video_photo, category_code

//        업로드 처리
//        비디오 실제 파일 이름
        String originalVideo = video.getOriginalFilename();
        log.info("oriVideo : " + originalVideo);

//        originalVideo의 마지막 부분 잘라서 온다
        String realVideo = originalVideo.substring(originalVideo.lastIndexOf("\\") + 1);

//        이미지 실제 파일 이름
        String originalImage = image.getOriginalFilename();
        String realImage = originalImage.substring(originalImage.lastIndexOf("\\") + 1);
        log.info("oriImg : " + originalImage);

        log.info("realVideo : " + realVideo);
        log.info("realImage : " + realImage);

//        UUID
        String uuid = UUID.randomUUID().toString();


//        실제로 저장할 파일 명 (위치포함)
        String saveVideo = uploadPath + File.separator + uuid + "_" + realVideo;
        String saveImage = uploadPath + File.separator + uuid + "_" + realImage;

        Path pathVideo = Paths.get(saveVideo);
        Path pathImage = Paths.get(saveImage);
        try {
            video.transferTo(pathVideo);
            image.transferTo(pathImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Video vo = new Video();

        vo.setVideoTitle(title);
        vo.setVideoDesc(desc);
        Category category = new Category();
        category.setCategoryCode(Integer.parseInt(categoryCode));
        vo.setCategory(category);
        vo.setVideoUrl(saveVideo);
        vo.setVideoPhoto(saveImage);
        Channel channel = new Channel();
        channel.setChannelCode(1);
        vo.setChannel(channel);
        Member member = new Member();
        member.setId("user1");
        vo.setMember(member);

//        return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.OK).body(videoService.create(vo));
    }


    // 영상 추가 : POST - http://localhost:8080/api/video
//    @PostMapping("/video")
//    public ResponseEntity<Video> newVideo(@RequestBody Video vo){
//        return ResponseEntity.status(HttpStatus.OK).body(videoService.create(vo));
//    }
    
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
