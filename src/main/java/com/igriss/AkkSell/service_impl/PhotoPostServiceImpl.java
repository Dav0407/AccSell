package com.igriss.AkkSell.service_impl;

import com.igriss.AkkSell.dtos.PhotoPostDTO;
import com.igriss.AkkSell.entities.PhotoPost;
import com.igriss.AkkSell.mappers.PhotoPostMapper;
import com.igriss.AkkSell.repositories.PhotoPostRepository;
import com.igriss.AkkSell.services.PhotoPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoPostServiceImpl implements PhotoPostService {

    private final PhotoPostRepository photoPostRepository;
    private final PhotoPostMapper photoPostMapper;

    @Override
    public PhotoPost createPhotoPost(PhotoPostDTO photoPostDTO, List<MultipartFile> photos) {
        List<String> photoPaths = savePhotos(photos);
        PhotoPost photoPost = photoPostMapper.convertToEntity(photoPostDTO);
        photoPost.setPhotoPaths(photoPaths);
        return photoPostRepository.save(photoPost);
    }

    @Override
    public Optional<PhotoPost> getPhotoPostById(Long id) {
        return photoPostRepository.findById(id);
    }

    public List<PhotoPost> getAllPhotoPosts() {
        return photoPostRepository.findAll();
    }

    @Override
    public PhotoPost updatePhotoPost(Long id, PhotoPostDTO photoPostDTO, List<MultipartFile> photos) {
        if (photoPostRepository.existsById(id)) {
            List<String> photoPaths = savePhotos(photos);
            PhotoPost photoPost = photoPostMapper.convertToEntity(photoPostDTO);
            photoPost.setPhotoPaths(photoPaths);
            photoPost.setId(id);
            return photoPostRepository.save(photoPost);
        } else {
            throw new RuntimeException("PhotoPost not found with id " + id);
        }
    }

    @Override
    public void deletePhotoPost(Long id) {
        photoPostRepository.deleteById(id);
    }

    private List<String> savePhotos(List<MultipartFile> photos) {
        return photos.stream()
                .map(this::savePhoto)
                .collect(Collectors.toList());
    }

    private String savePhoto(MultipartFile photo) {
        try {
            String fileName = photo.getOriginalFilename();
            Path filePath = Paths.get("photos/" + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, photo.getBytes());
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save photo", e);
        }
    }

}
