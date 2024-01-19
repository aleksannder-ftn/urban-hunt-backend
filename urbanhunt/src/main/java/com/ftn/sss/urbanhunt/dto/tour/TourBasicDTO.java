package com.ftn.sss.urbanhunt.dto.tour;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourBasicDTO {
    private Long id;
    private LocalDateTime startTime;
    private boolean accepted;
    private boolean finished;
    private Long realEstateId;
    private Long agentId;
    private Long agencyId;
    private Long guestId;


}
