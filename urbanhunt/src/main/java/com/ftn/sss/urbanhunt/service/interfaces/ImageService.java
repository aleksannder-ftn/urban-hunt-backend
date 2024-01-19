package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.model.Image;
import com.ftn.sss.urbanhunt.model.RealEstate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    List<Image> findAllByRealEstateId(Long id);
    /*
    void saveImage(Long id, String path, Long idd); */
    Image save(Image image);
}
