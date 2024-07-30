package com.igriss.AkkSell.services;

import com.igriss.AkkSell.dtos.PhotoPostDTO;
import com.igriss.AkkSell.entities.PhotoPost;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PhotoPostService {
    PhotoPost createPhotoPost(PhotoPostDTO photoPostDTO, List<MultipartFile> photos) throws IOException;
    Optional<PhotoPost> getPhotoPostById(Long id);
    List<PhotoPost> getAllPhotoPosts();
    PhotoPost updatePhotoPost(Long id, PhotoPostDTO photoPostDTO, List<MultipartFile> photos) throws IOException;
    void deletePhotoPost(Long id);
}
