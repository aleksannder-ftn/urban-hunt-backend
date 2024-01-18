package com.ftn.sss.urbanhunt.dto.agency;// Import statements...

import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.Tour;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgencyDetailedDTO {

    private Long id;
    private String name;
    private List<Long> toursIds;
    private List<Long> realEstatesIds;
    private Long ownerId;
    private Double report;

    // Static method to create an instance of AgencyDetailedDTO
    public static AgencyDetailedDTO getAgencyDetailedDTO(Agency agency) {
        return new AgencyDetailedDTO(
                agency.getId(),
                agency.getName(),
                agency.getTours().stream().map(Tour::getId).collect(Collectors.toList()),
                agency.getRealEstates().stream().map(RealEstate::getId).collect(Collectors.toList()),
                agency.getOwner().getId(),
                agency.getReport()
        );

    }
}
