package com.ftn.sss.urbanhunt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="agent")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Agent extends  User{

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tour> tours;

    @Column(name="average_rating", nullable = true)
    @JoinColumn(name = "average_rating")
    private float averageRating;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RealEstate> realEstateList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="agency_id", nullable = false)
    private Agency agency;

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
