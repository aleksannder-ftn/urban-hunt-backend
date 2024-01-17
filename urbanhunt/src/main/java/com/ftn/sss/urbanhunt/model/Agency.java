package com.ftn.sss.urbanhunt.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "agency")
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="agency_id")
    private Long id;

    @Column(name="agency_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tour> tours = new ArrayList<>();

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Agent> agents = new ArrayList<>();

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RealEstate> realEstates = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    // Ovo generise baza monthly, yearly etc.
    private Double report;

}
