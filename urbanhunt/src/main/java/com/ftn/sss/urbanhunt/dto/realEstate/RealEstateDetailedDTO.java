package com.ftn.sss.urbanhunt.dto.realEstate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealEstateDetailedDTO extends RealEstateBasicDTO{
    @Column(name = "acceptedTours")
    private Long numberOfActiveTours;
}
