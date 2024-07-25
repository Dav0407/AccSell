package com.igriss.AkkSell.services;

import com.igriss.AkkSell.dtos.VideoPostDTO;
import com.igriss.AkkSell.entities.VideoPost;

import java.util.List;
import java.util.Optional;

public interface VideoPostService {
    VideoPost createVideoPost(VideoPost videoPost);
    Optional<VideoPost> getVideoPostById(Long id);
    List<VideoPost> getAllVideoPosts();
    VideoPost updateVideoPost(Long id, VideoPost videoPost);
    void deleteVideoPost(Long id);
}
