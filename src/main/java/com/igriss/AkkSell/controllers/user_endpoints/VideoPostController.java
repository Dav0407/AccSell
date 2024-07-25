package com.igriss.AkkSell.controllers.user_endpoints;

import com.igriss.AkkSell.dtos.VideoPostDTO;
import com.igriss.AkkSell.entities.User;
import com.igriss.AkkSell.entities.VideoPost;
import com.igriss.AkkSell.mappers.VideoPostMapper;
import com.igriss.AkkSell.services.VideoPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video-posts")
@RequiredArgsConstructor
public class VideoPostController {

    private final VideoPostService videoPostService;
    private final VideoPostMapper videoPostMapper;

    @PostMapping
    public ResponseEntity<VideoPostDTO> createVideoPost(@RequestBody VideoPostDTO videoPostDTO) {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        videoPostDTO.setUserId(userDetails.getId());

        VideoPost videoPost = videoPostMapper.convertToEntity(videoPostDTO);
        VideoPost createdVideoPost = videoPostService.createVideoPost(videoPost);
        return ResponseEntity.ok(videoPostMapper.convertToDTO(createdVideoPost));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoPostDTO> getVideoPostById(@PathVariable Long id) {
        return videoPostService.getVideoPostById(id)
                .map(videoPost -> ResponseEntity.ok(videoPostMapper.convertToDTO(videoPost)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<VideoPostDTO>> getAllVideoPosts() {
        List<VideoPost> videoPosts = videoPostService.getAllVideoPosts();
        List<VideoPostDTO> videoPostDTOs = videoPosts.stream()
                .map(videoPostMapper::convertToDTO)
                .toList();
        return ResponseEntity.ok(videoPostDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoPostDTO> updateVideoPost(@PathVariable Long id, @RequestBody VideoPostDTO videoPostDTO) {
        try {
            VideoPost videoPost = videoPostMapper.convertToEntity(videoPostDTO);
            VideoPost updatedVideoPost = videoPostService.updateVideoPost(id, videoPost);
            return ResponseEntity.ok(videoPostMapper.convertToDTO(updatedVideoPost));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoPost(@PathVariable Long id) {
        videoPostService.deleteVideoPost(id);
        return ResponseEntity.noContent().build();
    }
}
