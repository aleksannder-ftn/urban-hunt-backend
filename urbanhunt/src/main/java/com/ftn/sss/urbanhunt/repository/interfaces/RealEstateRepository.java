package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.enums.RealEstateType;
import com.ftn.sss.urbanhunt.model.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
    RealEstate findRealEstateById(Long id);

    @Query("SELECT re FROM RealEstate re WHERE :agent IS NULL OR re.agent = :agent " +
            "AND (:location IS NULL OR re.location LIKE %:location%) " +
            "AND (:surfaceFrom IS NULL OR re.surfaceArea >= :surfaceFrom) " +
            "AND (:surfaceTo IS NULL OR re.surfaceArea <= :surfaceTo) " +
            "AND (:priceFrom IS NULL OR re.price >= :priceFrom) " +
            "AND (:priceTo IS NULL OR re.price <= :priceTo) " +
            "AND (:type IS NULL OR :type = '' OR re.realEstateType = :type) " +
            "AND (:transactionType IS NULL OR :transactionType = '' OR re.transactionType = :transactionType)")
    List<RealEstate> findAllByAgentIdAndOptionalFields(
            @Param("agent") Agent agent,
            @Param("location") String location,
            @Param("surfaceFrom") Float surfaceFrom,
            @Param("surfaceTo") Float surfaceTo,
            @Param("priceFrom") Float priceFrom,
            @Param("priceTo") Float priceTo,
            @Param("type") RealEstateType type,
            @Param("transactionType") TransactionType transactionType);
}