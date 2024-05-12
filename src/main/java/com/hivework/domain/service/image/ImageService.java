package com.hivework.domain.service.image;

import com.hivework.domain.entity.image.Image;
import com.hivework.domain.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageRepository imageRepository;


    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image findById(Long id){
        return imageRepository.findById(id).orElse(null);
    }

    public Image save(Image image){
        return imageRepository.save(image);
    }
}


