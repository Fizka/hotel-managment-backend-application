package com.project.engineer.project.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Reservet")
public class Reservet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idReservet;

    @Column(name = "dateReservation")
    private LocalDate dateReservation;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "customer_id",
            nullable = true
    )
    @JsonIgnore
    private Customer customerReservation;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "room_id", nullable = true)
    @JsonIgnore
    private Room roomReservation;

    public Reservet() {
    }

    public Reservet(LocalDate dateReservation, Customer customerReservation, Room roomReservation) {
        this.dateReservation = dateReservation;
        this.customerReservation = customerReservation;
        this.roomReservation = roomReservation;
    }




}

