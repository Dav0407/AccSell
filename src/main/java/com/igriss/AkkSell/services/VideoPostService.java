package com.igriss.AkkSell.services;

import com.igriss.AkkSell.dtos.VideoPostDTO;
import com.igriss.AkkSell.entities.VideoPost;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface VideoPostService {
    VideoPost createVideoPost(VideoPostDTO videoPostDTO, MultipartFile videoFile) throws IOException;
    Optional<VideoPost> getVideoPostById(Long id);
    List<VideoPost> getAllVideoPosts();
    VideoPost updateVideoPost(Long id, VideoPostDTO videoPostDTO, MultipartFile videoFile) throws IOException;
    void deleteVideoPost(Long id);
}
