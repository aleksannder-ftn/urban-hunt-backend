package com.ftn.sss.urbanhunt.dto.mapper;

import com.ftn.sss.urbanhunt.dto.agency.AgencyBasicDTO;
import com.ftn.sss.urbanhunt.dto.agency.AgencyDetailedDTO;
import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.model.Owner;
import com.ftn.sss.urbanhunt.model.RealEstate;
import com.ftn.sss.urbanhunt.model.Tour;
import com.ftn.sss.urbanhunt.service.interfaces.OwnerService;
import com.ftn.sss.urbanhunt.service.interfaces.UserService;

import java.util.stream.Collectors;

public class AgencyMapper {

    public static AgencyBasicDTO toAgencyBasicDTO(Agency agency) {
        if (agency == null) return null;

        AgencyBasicDTO dto = new AgencyBasicDTO();
        return getAgencyBasicDTO(agency, dto);
    }

    public static AgencyDetailedDTO toAgencyDetailedDTO(Agency agency) {
        if (agency == null) return null;

        AgencyDetailedDTO dto = new AgencyDetailedDTO();
        return getAgencyDetailedDTO(agency, dto);
    }

    static AgencyBasicDTO getAgencyBasicDTO(Agency agency, AgencyBasicDTO agencyBasicDTO) {
        agencyBasicDTO.setId(agency.getId());
        agencyBasicDTO.setName(agency.getName());
        agencyBasicDTO.setOwnerId(agency.getOwner().getId());

        return agencyBasicDTO;
    }

    static AgencyDetailedDTO getAgencyDetailedDTO(Agency agency, AgencyDetailedDTO agencyDetailedDTO) {
        agencyDetailedDTO.setId(agency.getId());
        agencyDetailedDTO.setName(agency.getName());
        agencyDetailedDTO.setOwnerId(agency.getOwner().getId());

        agencyDetailedDTO.setRealEstatesIds(
                agency.getRealEstates().stream().map(RealEstate::getId).collect(Collectors.toList())
        );

        agencyDetailedDTO.setToursIds(
                agency.getTours().stream().map(Tour::getId).collect(Collectors.toList())
        );

        agencyDetailedDTO.setReport(agency.getReport());

        return agencyDetailedDTO;
    }

    public static Agency toAgencyEntity(AgencyBasicDTO agencyBasicDTO, OwnerService ownerService) {
        if (agencyBasicDTO == null) return null;

        Agency agency = new Agency();
        return getAgencyEntity(agencyBasicDTO, agency, ownerService);
    }
/*
    public static Agency toAgencyEntity(AgencyDetailedDTO agencyDetailedDTO) {
        if (agencyDetailedDTO == null) return null;

        Agency agency = new Agency();
        return getAgencyEntityDetailed(agencyDetailedDTO, agency);
    } */


    static Agency getAgencyEntity(AgencyBasicDTO agencyBasicDTO, Agency agency, OwnerService ownerService) {
        agency.setId(agencyBasicDTO.getId());
        agency.setName(agencyBasicDTO.getName());
        agency.setOwner(ownerService.findOwnerById(agencyBasicDTO.getOwnerId()));

        return agency;
    }
/*
    static Agency getAgencyEntityDetailed(AgencyDetailedDTO agencyDetailedDTO, Agency agency, UserService userService) {
        agency.setId(agencyDetailedDTO.getId());
        agency.setName(agencyDetailedDTO.getName());
        agency.setOwner((Owner) userService.findUserById(agencyDetailedDTO.getOwnerId()));
        agency.setRealEstates(agencyDetailedDTO.getRealEstates());
        agency.setTours(agencyDetailedDTO.getTours());
        agency.setReport(agencyDetailedDTO.getReport());

        return agency;
    }
*/
}
