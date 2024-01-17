package com.ftn.sss.urbanhunt.dto.mapper;

import com.ftn.sss.urbanhunt.dto.agency.AgencyBasicDTO;
import com.ftn.sss.urbanhunt.model.Agency;

public class AgencyMapper {

    public static AgencyBasicDTO toAgencyBasicDTO(Agency agency) {
        if (agency == null) return null;

        AgencyBasicDTO dto = new AgencyBasicDTO();
        return getAgencyBasicDTO(agency, dto);
    }

    static AgencyBasicDTO getAgencyBasicDTO(Agency agency, AgencyBasicDTO agencyBasicDTO) {
        agencyBasicDTO.setId(agency.getId());
        agencyBasicDTO.setName(agency.getName());
        agencyBasicDTO.setOwner(agency.getOwner());

        return agencyBasicDTO;
    }

    public static Agency toAgencyEntity(AgencyBasicDTO agencyBasicDTO) {
        if (agencyBasicDTO == null) return null;

        Agency agency = new Agency();
        return getAgencyEntity(agencyBasicDTO, agency);
    }

    static Agency getAgencyEntity(AgencyBasicDTO agencyBasicDTO, Agency agency) {
        agency.setId(agencyBasicDTO.getId());
        agency.setName(agencyBasicDTO.getName());
        agency.setOwner(agencyBasicDTO.getOwner());

        return agency;
    }


}
