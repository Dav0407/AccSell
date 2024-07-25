package com.igriss.AkkSell.service_impl;

import com.igriss.AkkSell.dtos.VideoPostDTO;
import com.igriss.AkkSell.entities.VideoPost;
import com.igriss.AkkSell.repositories.VideoPostRepository;
import com.igriss.AkkSell.services.VideoPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoPostServiceImpl implements VideoPostService {

    private final VideoPostRepository videoPostRepository;

    @Override
    public VideoPost createVideoPost(VideoPost videoPost) {
        return videoPostRepository.save(videoPost);
    }

    @Override
    public Optional<VideoPost> getVideoPostById(Long id) {
        return videoPostRepository.findById(id);
    }

    @Override
    public List<VideoPost> getAllVideoPosts() {
        return videoPostRepository.findAll();
    }

    @Override
    public VideoPost updateVideoPost(Long id, VideoPost videoPost) {
        if (videoPostRepository.existsById(id)) {
            videoPost.setId(id);
            return videoPostRepository.save(videoPost);
        } else {
            throw new RuntimeException("VideoPost not found with id " + id);
        }
    }

    @Override
    public void deleteVideoPost(Long id) {
        videoPostRepository.deleteById(id);
    }
}
