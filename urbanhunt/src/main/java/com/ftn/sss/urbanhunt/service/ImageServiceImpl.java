package com.ftn.sss.urbanhunt.service;

import com.ftn.sss.urbanhunt.model.Image;
import com.ftn.sss.urbanhunt.repository.interfaces.ImageRepository;
import com.ftn.sss.urbanhunt.service.interfaces.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> findAllByRealEstateId(Long id) {
        return imageRepository.findAllByRealEstateId(id);
    }

    /*
    @Override
    public void saveImage(Long id, String path, Long idd) {
        imageRepository.saveImage(id, path, idd);
    } */

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }
}
