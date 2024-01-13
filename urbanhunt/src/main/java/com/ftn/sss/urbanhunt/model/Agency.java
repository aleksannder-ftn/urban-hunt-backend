package com.ftn.sss.urbanhunt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agency {

    private Long id;
    private List<Tour> tours;
    private List<Agent> agents;
    private List<RealEstate> realEstates;
    private Owner owner;
    // Ovo generise baza monthly, yearly etc.
    private Double report;

}
