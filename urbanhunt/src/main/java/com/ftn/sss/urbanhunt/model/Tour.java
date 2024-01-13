package com.ftn.sss.urbanhunt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tour {

    private Long id;
    private LocalDateTime startTime;
    private boolean accepted;
    private boolean finished;
}
