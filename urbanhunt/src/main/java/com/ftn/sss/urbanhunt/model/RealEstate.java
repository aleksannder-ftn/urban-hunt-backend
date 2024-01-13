package com.ftn.sss.urbanhunt.model;

import com.ftn.sss.urbanhunt.model.enums.RealEstateType;
import com.ftn.sss.urbanhunt.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealEstate {

    private Long id;
    private String location;
    private float surfaceArea;
    private float price;
    private String image;
    private int viewCount;
    private float rating;
    List<Tour> tours;
    private RealEstateType realEstateType;
    private TransactionType transactionType;
    private boolean active;
}
