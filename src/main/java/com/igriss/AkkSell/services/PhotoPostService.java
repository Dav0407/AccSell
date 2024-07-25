package com.igriss.AkkSell.services;

import com.igriss.AkkSell.entities.PhotoPost;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PhotoPostService {
    PhotoPost createPhotoPost(PhotoPost photoPost, List<MultipartFile> photos);
    Optional<PhotoPost> getPhotoPostById(Long id);
    List<PhotoPost> getAllPhotoPosts();
    PhotoPost updatePhotoPost(Long id, PhotoPost photoPost, List<MultipartFile> photos);
    void deletePhotoPost(Long id);
}
