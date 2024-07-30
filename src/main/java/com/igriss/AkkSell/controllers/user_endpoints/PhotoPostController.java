package com.igriss.AkkSell.controllers.user_endpoints;

import com.igriss.AkkSell.dtos.PhotoPostDTO;
import com.igriss.AkkSell.entities.PhotoPost;
import com.igriss.AkkSell.entities.User;
import com.igriss.AkkSell.mappers.PhotoPostMapper;
import com.igriss.AkkSell.services.PhotoPostService;
import com.igriss.AkkSell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/photo-posts")
@RequiredArgsConstructor
public class PhotoPostController {

    private final PhotoPostService photoPostService;
    private final PhotoPostMapper photoPostMapper;
    private final UserService userService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<PhotoPostDTO> createPhotoPost(@ModelAttribute PhotoPostDTO photoPostDTO) throws IOException {

        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());

        photoPostDTO.setUserId(user.getId());

        PhotoPost createdPhotoPost = photoPostService.createPhotoPost(photoPostDTO, photoPostDTO.getPhotoFiles());
        return ResponseEntity.ok(photoPostMapper.convertToDTO(createdPhotoPost));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoPostDTO> getPhotoPostById(@PathVariable Long id) {
        return photoPostService.getPhotoPostById(id)
                .map(photoPost -> ResponseEntity.ok(photoPostMapper.convertToDTO(photoPost)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<PhotoPostDTO>> getAllPhotoPosts() {
        List<PhotoPost> photoPosts = photoPostService.getAllPhotoPosts();
        List<PhotoPostDTO> photoPostDTOs = photoPosts.stream()
                .map(photoPostMapper::convertToDTO)
                .toList();
        return ResponseEntity.ok(photoPostDTOs);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<PhotoPostDTO> updatePhotoPost(@PathVariable Long id, @ModelAttribute PhotoPostDTO photoPostDTO) throws IOException {
        try {
            PhotoPost updatedPhotoPost = photoPostService.updatePhotoPost(id, photoPostDTO, photoPostDTO.getPhotoFiles());
            return ResponseEntity.ok(photoPostMapper.convertToDTO(updatedPhotoPost));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhotoPost(@PathVariable Long id) {
        photoPostService.deletePhotoPost(id);
        return ResponseEntity.noContent().build();
    }
}
