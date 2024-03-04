package com.ftn.sss.urbanhunt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="agent_rating")
public class AgentRating {

    @Id
    @ManyToOne
    @JoinColumn(name="agent_id")
    private Agent agent;

    @Id
    @ManyToOne
    @JoinColumn(name="guest_id")
    private Guest guest;

    @Column(name="rating", nullable = false)
    private Integer rating;

    @Column(name="message", nullable = true)
    private String message;

    @Id
    @ManyToOne
    @JoinColumn(name="real_estate_id")
    private RealEstate realEstate;


}
