package com.igriss.AkkSell.controllers.user_endpoints;

import com.igriss.AkkSell.entities.PhotoPost;
import com.igriss.AkkSell.entities.User;
import com.igriss.AkkSell.game_type.GameType;
import com.igriss.AkkSell.services.PhotoPostService;
import com.igriss.AkkSell.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/photo-posts")
@RequiredArgsConstructor
public class PhotoPostController {

    private final PhotoPostService photoPostService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<PhotoPost> createPhotoPost(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") BigDecimal price,
            @RequestParam("gameType") GameType gameType,
            @RequestParam("photos") List<MultipartFile> photos) {

        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByEmail(userDetails.getUsername());

        PhotoPost photoPost = PhotoPost.builder()
                .title(title)
                .description(description)
                .price(price)
                .gameType(gameType)
                .user(user)
                .build();

        PhotoPost createdPhotoPost = photoPostService.createPhotoPost(photoPost, photos);
        return ResponseEntity.ok(createdPhotoPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoPost> getPhotoPostById(@PathVariable Long id) {
        return photoPostService.getPhotoPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PhotoPost>> getAllPhotoPosts() {
        List<PhotoPost> photoPosts = photoPostService.getAllPhotoPosts();
        return ResponseEntity.ok(photoPosts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotoPost> updatePhotoPost(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") BigDecimal price,
            @RequestParam("gameType") GameType gameType,
            @RequestParam("photos") List<MultipartFile> photos) {

        PhotoPost photoPost = PhotoPost.builder()
                .title(title)
                .description(description)
                .price(price)
                .gameType(gameType)
                .build();

        try {
            PhotoPost updatedPhotoPost = photoPostService.updatePhotoPost(id, photoPost, photos);
            return ResponseEntity.ok(updatedPhotoPost);
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
