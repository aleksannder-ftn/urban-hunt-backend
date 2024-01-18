package com.ftn.sss.urbanhunt.service.interfaces;

import com.ftn.sss.urbanhunt.dto.agency.AgencyBasicDTO;
import com.ftn.sss.urbanhunt.model.Agency;
import com.ftn.sss.urbanhunt.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerService {
    Owner findOwnerById(Long id);
}
