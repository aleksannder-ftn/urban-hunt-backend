package com.ftn.sss.urbanhunt.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tour_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name="start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name="end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name="accepted", nullable = false)
    private boolean accepted;

    @Column(name="finished", nullable = false)
    private boolean finished;

    @ManyToOne(fetch = FetchType.LAZY)
    private RealEstate realEstate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Agency agency;

    @ManyToOne(fetch = FetchType.LAZY)
    private Guest guest;
}
