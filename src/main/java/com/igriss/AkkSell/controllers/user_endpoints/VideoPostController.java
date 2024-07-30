package com.igriss.AkkSell.controllers.user_endpoints;

import com.igriss.AkkSell.dtos.VideoPostDTO;
import com.igriss.AkkSell.entities.User;
import com.igriss.AkkSell.entities.VideoPost;
import com.igriss.AkkSell.mappers.VideoPostMapper;
import com.igriss.AkkSell.services.UserService;
import com.igriss.AkkSell.services.VideoPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/video-posts")
@RequiredArgsConstructor
public class VideoPostController {

    private final VideoPostService videoPostService;
    private final VideoPostMapper videoPostMapper;
    private final UserService userService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<VideoPostDTO> createVideoPost(@ModelAttribute VideoPostDTO videoPostDTO) throws IOException {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());

        videoPostDTO.setUserId(user.getId());

        VideoPost createdVideoPost = videoPostService.createVideoPost(videoPostDTO, videoPostDTO.getVideoFile());
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

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<VideoPostDTO> updateVideoPost(@PathVariable Long id, @ModelAttribute VideoPostDTO videoPostDTO) throws IOException {
        try {
            VideoPost updatedVideoPost = videoPostService.updateVideoPost(id, videoPostDTO, videoPostDTO.getVideoFile());
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
