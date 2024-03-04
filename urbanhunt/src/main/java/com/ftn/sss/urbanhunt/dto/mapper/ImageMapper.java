package com.ftn.sss.urbanhunt.dto.mapper;

import com.ftn.sss.urbanhunt.dto.image.ImageDTO;
import com.ftn.sss.urbanhunt.model.Image;
import com.ftn.sss.urbanhunt.service.interfaces.RealEstateService;

public class ImageMapper {
    public static ImageDTO toImageDTO(Image image) {
        if (image == null) return null;

        ImageDTO dto = new ImageDTO();
        return getImageDTO(image, dto);
    }

    static ImageDTO getImageDTO(Image image, ImageDTO dto) {
        dto.setId(image.getId());
        dto.setImagePath(image.getImagePath());
        dto.setRealEstateId(image.getRealEstate().getId());
        return dto;
    }

    public static Image toImageEntity(ImageDTO dto, RealEstateService realEstateService) {
        if (dto == null) return null;

        Image img = new Image();

        img.setId(dto.getId());
        img.setImagePath(dto.getImagePath());
        img.setRealEstate(realEstateService.findRealEstateById(dto.getRealEstateId()));

        return img;
    }
}
