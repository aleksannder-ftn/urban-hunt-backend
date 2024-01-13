package com.ftn.sss.urbanhunt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agent extends  User{

    private ArrayList<Tour> tours;
    private float averageRating;
    private ArrayList<RealEstate> realEstateList;

}
