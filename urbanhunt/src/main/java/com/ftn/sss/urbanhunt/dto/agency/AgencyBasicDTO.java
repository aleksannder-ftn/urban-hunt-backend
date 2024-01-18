package com.ftn.sss.urbanhunt.dto.agency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgencyBasicDTO {
    private Long id;
    private String name;
    private Long ownerId;
}
