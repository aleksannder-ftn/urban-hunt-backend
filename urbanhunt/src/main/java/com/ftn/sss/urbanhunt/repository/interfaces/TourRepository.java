package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    List<Tour> getAllByAcceptedAndFinished(boolean accepted, boolean finished);

    List<Tour> findAllByAgentIdAndAcceptedAndFinished(Long agentId, boolean accepted, boolean finished);

    List<Tour> findAllByAgencyIdAndAcceptedAndFinished(Long agencyId, boolean accepted, boolean finished);
}
