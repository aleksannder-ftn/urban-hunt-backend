package com.ftn.sss.urbanhunt.dto.realEstate;

import com.ftn.sss.urbanhunt.model.enums.RealEstateType;
import com.ftn.sss.urbanhunt.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealEstateBasicDTO {

    private Long id;
    private boolean active;
    private String location;
    private float price;
    private RealEstateType realEstateType;
    private Long agentId;
    private Long agencyId;
    // under are nullable
    private Float rating;
    private Float surfaceArea;
    private TransactionType transactionType;
    private Integer viewCount;
}
