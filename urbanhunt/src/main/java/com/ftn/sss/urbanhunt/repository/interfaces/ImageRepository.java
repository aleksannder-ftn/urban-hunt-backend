package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.Image;
import com.ftn.sss.urbanhunt.model.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByRealEstateId(Long id);
    Image save(Image image);

    void deleteByRealEstate(RealEstate realEstate);
}
