package com.ftn.sss.urbanhunt.dto.agent;

import com.ftn.sss.urbanhunt.dto.user.UserBasicDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgentBasicDTO extends UserBasicDTO {
    private Long agencyId;
    private Float averageRating;
}
