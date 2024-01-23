package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
    RealEstate findRealEstateById(Long id);
    List<RealEstate> findAllByAgentIdAndOptionalFields(@Param("agent_id") Long id, @Param("location") String location, @Param("surface_area_from") Double surfaceFrom,
                                                       @Param("surface_area_to") Double surfaceTo, @Param("price_from") Double priceFrom, @Param("price_to") Double priceTo,
                                                       @Param("real_estate_type") String type, @Param("transaction_type") String transactionType);
}