package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.model.Image;
import com.ftn.sss.urbanhunt.model.RealEstate;

import java.util.List;

public interface ImageService {
    List<Image> findAllByRealEstateId(Long id);
    Image save(Image image);
    void deleteAllByRealEstate(RealEstate realEstate);
}
