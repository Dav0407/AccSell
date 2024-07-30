package com.igriss.AkkSell.service_impl;

import com.igriss.AkkSell.dtos.VideoPostDTO;
import com.igriss.AkkSell.entities.VideoPost;
import com.igriss.AkkSell.mappers.VideoPostMapper;
import com.igriss.AkkSell.repositories.VideoPostRepository;
import com.igriss.AkkSell.services.VideoPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoPostServiceImpl implements VideoPostService {

    private final VideoPostRepository videoPostRepository;
    private final VideoPostMapper videoPostMapper;
    private static final String VIDEO_STORAGE_PATH = "src/main/resources/post_videos";

    @Override
    public VideoPost createVideoPost(VideoPostDTO videoPostDTO, MultipartFile videoFile) throws IOException {
        saveVideoFile(videoFile);
        VideoPost videoPost = videoPostMapper.convertToEntity(videoPostDTO);
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
    public VideoPost updateVideoPost(Long id, VideoPostDTO videoPostDTO, MultipartFile videoFile) throws IOException {
        if (videoPostRepository.existsById(id)) {
            saveVideoFile(videoFile);
            VideoPost videoPost = videoPostMapper.convertToEntity(videoPostDTO);
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

    private void saveVideoFile(MultipartFile videoFile) throws IOException {
        String filePath = VIDEO_STORAGE_PATH + File.separator + videoFile.getOriginalFilename();
        Files.write(Paths.get(filePath), videoFile.getBytes());
    }
}
