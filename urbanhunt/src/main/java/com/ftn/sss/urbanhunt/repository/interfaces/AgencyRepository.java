package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.dto.agency.AgencyBasicDTO;
import com.ftn.sss.urbanhunt.model.Agency;

public interface AgencyRepository {

    Agency createAgency(Agency agency);
}
