package com.ftn.sss.urbanhunt.dto.mapper;

import com.ftn.sss.urbanhunt.dto.agency.AgencyBasicDTO;
import com.ftn.sss.urbanhunt.dto.agency.AgencyDetailedDTO;
import com.ftn.sss.urbanhunt.model.Agency;

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
        agencyBasicDTO.setOwner(agency.getOwner());

        return agencyBasicDTO;
    }

    static AgencyDetailedDTO getAgencyDetailedDTO(Agency agency, AgencyDetailedDTO agencyDetailedDTO) {
        agencyDetailedDTO.setId(agency.getId());
        agencyDetailedDTO.setName(agency.getName());
        agencyDetailedDTO.setOwner(agency.getOwner());
        agencyDetailedDTO.setRealEstates(agency.getRealEstates());
        agencyDetailedDTO.setTours(agency.getTours());
        agencyDetailedDTO.setReport(agency.getReport());

        return agencyDetailedDTO;
    }
    public static Agency toAgencyEntity(AgencyBasicDTO agencyBasicDTO) {
        if (agencyBasicDTO == null) return null;

        Agency agency = new Agency();
        return getAgencyEntity(agencyBasicDTO, agency);
    }

    public static Agency toAgencyEntity(AgencyDetailedDTO agencyDetailedDTO) {
        if (agencyDetailedDTO == null) return null;

        Agency agency = new Agency();
        return getAgencyEntityDetailed(agencyDetailedDTO, agency);
    }


    static Agency getAgencyEntity(AgencyBasicDTO agencyBasicDTO, Agency agency) {
        agency.setId(agencyBasicDTO.getId());
        agency.setName(agencyBasicDTO.getName());
        agency.setOwner(agencyBasicDTO.getOwner());

        return agency;
    }

    static Agency getAgencyEntityDetailed(AgencyDetailedDTO agencyDetailedDTO, Agency agency) {
        agency.setId(agencyDetailedDTO.getId());
        agency.setName(agencyDetailedDTO.getName());
        agency.setOwner(agencyDetailedDTO.getOwner());
        agency.setRealEstates(agencyDetailedDTO.getRealEstates());
        agency.setTours(agencyDetailedDTO.getTours());
        agency.setReport(agencyDetailedDTO.getReport());

        return agency;
    }

}
