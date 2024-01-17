package com.ftn.sss.urbanhunt.dto.agency;

import com.ftn.sss.urbanhunt.model.Owner;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.Tour;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgencyDetailedDTO {

    private Long id;
    private String name;
    private List<Tour> tours;
    private List<RealEstate> realEstates;
    private Owner owner;
    private Double report;

}
