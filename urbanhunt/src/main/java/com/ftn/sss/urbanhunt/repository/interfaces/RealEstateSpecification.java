package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.Agent;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.enums.RealEstateType;
import com.ftn.sss.urbanhunt.model.enums.TransactionType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public interface RealEstateSpecification {

    static Specification<RealEstate> withOptionalFields (
            Agent agent, String location, Float surfaceFrom,
            Float surfaceTo, Float priceFrom, Float priceTo,
            RealEstateType realEstateType, TransactionType transactionType) {
        return (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (agent != null) predicateList.add(cb.equal(root.get("agent"), agent));

            if (location != null) predicateList.add(cb.like(root.get("location"), "%" + location + "%"));

            if (surfaceFrom != null) predicateList.add(cb.greaterThanOrEqualTo(root.get("surfaceArea"), surfaceFrom));

            if (surfaceTo != null) predicateList.add(cb.lessThanOrEqualTo(root.get("surfaceArea"), surfaceTo));

            if (priceFrom != null) predicateList.add(cb.greaterThanOrEqualTo(root.get("price"), priceFrom));

            if (priceTo != null) predicateList.add(cb.lessThanOrEqualTo(root.get("price"), priceTo));

            if (realEstateType != null) predicateList.add(cb.equal(root.get("realEstateType"), realEstateType));

            if (transactionType != null) predicateList.add(cb.equal(root.get("transactionType"), transactionType));

            return cb.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
