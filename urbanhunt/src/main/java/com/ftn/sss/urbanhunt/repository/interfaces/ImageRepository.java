package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByRealEstateId(Long id);

    /*
    @Query("INSERT INTO Image (id, imagePath, realEstate) VALUES (:imageId, :imagePath, :realEstateId)")
    void saveImage(@Param("imageId") Long imageId, @Param("imagePath") String imagePath, @Param("realEstateId") Long realEstateId); */

    Image save(Image image);
}
