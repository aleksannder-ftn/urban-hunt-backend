package com.ftn.sss.urbanhunt.model;

import com.ftn.sss.urbanhunt.model.enums.RealEstateType;
import com.ftn.sss.urbanhunt.model.enums.TransactionType;
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
@Table(name="real_estate")
public class RealEstate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="real_estate_id")
    private Long id;

    @Column(name="location", nullable = false)
    private String location;

    @Column(name="surface_area")
    private float surfaceArea;

    @Column(name="price", nullable = false)
    private float price;

    @OneToMany(mappedBy = "realEstate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> image = new ArrayList<>();

    @Column(name="view_count")
    private Integer viewCount;

    @Column(name="rating")
    private Float rating;

    @OneToMany(mappedBy = "realEstate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Tour> tours;

    @Column(name="real_estate_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RealEstateType realEstateType;

    @Column(name="transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name="active", nullable = false)
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="agent_id", nullable = false)
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Agency agency;
}
