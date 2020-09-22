package com.project.engineer.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idReservation;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "startData")
    Date startData;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "endData")
    Date endData;

    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "room_id", nullable = true)
    @JsonIgnore
    private Room roomReservation;

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

    public Reservation() {
    }

    public Reservation(Date startData, Date endData) {
        this.startData = startData;
        this.endData = endData;
    }

    public Room getRoomReservation() {
        return roomReservation;
    }

    public void setRoomReservation(Room roomReservation) {
        this.roomReservation = roomReservation;
    }

    public Customer getCustomerReservation() {
        return customerReservation;
    }

    public void setCustomerReservation(Customer customerReservation) {
        this.customerReservation = customerReservation;
    }

    public long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(long idReservation) {
        this.idReservation = idReservation;
    }

    public Date getStartData() {
        return startData;
    }

    public void setStartData(Date startData) {
        this.startData = startData;
    }

    public Date getEndData() {
        return endData;
    }

    public void setEndData(Date endData) {
        this.endData = endData;
    }


}
