package com.kh.youtube.controller;

import com.kh.youtube.domain.Channel;
import com.kh.youtube.domain.Subscribe;
import com.kh.youtube.domain.Video;
import com.kh.youtube.service.ChannelService;
import com.kh.youtube.service.SubscribeService;
import com.kh.youtube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private VideoService videoService;


    @Autowired
    private SubscribeService subscribeService;




    // 채널 조회 : GET - http://localhost:8080/api/channel/1
    @GetMapping("/channel/{id}")
    public ResponseEntity<Channel> showChannel(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(channelService.show(id));
    }
    
    // 채널에 존재하는 영상 조회 : GET - http://localhost:8080/api/channel/1/video
    @GetMapping("/channel/{id}/video")
    public ResponseEntity<List<Video>> showVideoList(@PathVariable int id){

        // SELECT * FROM VIDEO WHERE CHANNEL_CODE = ?
        return ResponseEntity.status(HttpStatus.OK).body(videoService.findByChannelCode(id));
    }


    
    // 채널 추가 : POST - http://localhost:8080/api/channel

    @PostMapping("/channel")
    public ResponseEntity<Channel> createChannel(@RequestBody Channel vo){
        return ResponseEntity.status(HttpStatus.OK).body(channelService.create(vo));
    }
    
    
    // 채널 수정 : PUT - http://localhost:8080/api/channel
    @PutMapping("/channel")
    public ResponseEntity<Channel> updateChannel(@RequestBody Channel vo){
        return ResponseEntity.status(HttpStatus.OK).body(channelService.update(vo));
    }
    
    // 채널 삭제 : DELETE - http://localhost:8080/api/channel/1
    @DeleteMapping("/channel/{id}")
    public ResponseEntity<Channel> deleteChannel(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(channelService.delete(id));
    }
    
    // 내가 구독한 채널 조회 : GET - http://localhost:8080/api/subscribe/user1

    @GetMapping("/subscribe/{user}")
    public ResponseEntity<List<Subscribe>> showSubChannel(@PathVariable String user){
        return ResponseEntity.status(HttpStatus.OK).body(subscribeService.findByMemberId(user));
    }

    
    // 채널 구독 : POST - http://localhost:8080/api/subscribe
    @PostMapping("/subscribe")
    public ResponseEntity<Subscribe> subChannel(@RequestBody Subscribe vo){
        return ResponseEntity.status(HttpStatus.OK).body(subscribeService.create(vo));
    }


    
    // 채널 구독 취소 : DELETE - http://localhost:8080/api/subscribe/1

    @DeleteMapping("/subscribe/{id}")
    public ResponseEntity<Subscribe> subChannel(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(subscribeService.delete(id));
    }

}
