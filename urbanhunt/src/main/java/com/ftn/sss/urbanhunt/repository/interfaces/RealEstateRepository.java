package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.RealEstate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long>, JpaSpecificationExecutor<RealEstate> {
    RealEstate findRealEstateById(Long id);
    List<RealEstate> findAll(Specification<RealEstate> spec);
    @Query(nativeQuery = true,
            value = "SELECT re.real_estate_id, re.location, re.price, re.surface_area, re.transaction_type, re.real_estate_type, COUNT(t.accepted) as acceptedTours " +
                    "FROM real_estate re " +
                    "LEFT JOIN tour t " +
                    "ON t.real_estate_real_estate_id = re.real_estate_id " +
                    "WHERE t.accepted = true " +
                    "AND t.finished = false " +
                    "GROUP BY re.real_estate_id " +
                    "ORDER BY acceptedTours DESC")
    List<Object[]> findAllByPopularity();

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE real_estate SET active = false, is_rented = true WHERE real_estate_id = :realEstateId")
    void rentRealEstate(Long realEstateId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE real_estate SET active = false, is_sold = true WHERE real_estate_id = :realEstateId")
    void buyRealEstate(Long realEstateId);

    List<RealEstate> findByActiveTrue();

    @Query(nativeQuery = true,
            value = "SELECT SUM(re.price) " +
                    "FROM real_estate re " +
                    "WHERE re.is_sold = true")
    Float sumPricesOfSoldRealEstates();

    @Query(nativeQuery = true,
            value = "SELECT SUM(re.price) " +
                    "FROM real_estate re " +
                    "WHERE re.is_rented = true")
    Float sumPricesOfRentedRealEstates();
}