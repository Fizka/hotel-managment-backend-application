package com.project.engineer.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idReservation;

    @Column(name = "startData")
    LocalDate startData;

    @Column(name = "endData")
    LocalDate endData;

    @Column(name = "howLong")
    int howLong;

    @Column(name = "customer")
    String customer;

    @Column(name = "room")
    String room;

    @Column(name = "status")
    boolean status;

    @ManyToOne(
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

    public Reservation(boolean status, LocalDate startData, LocalDate endData, int howLong, Room roomReservation, Customer customerReservation) {
        this.status = status;
        this.startData = startData;
        this.endData = endData;
        this.howLong = howLong;
        this.roomReservation = roomReservation;
        this.customerReservation = customerReservation;
    }

    public Reservation(LocalDate startData, LocalDate endData) {
        this.startData = startData;
        this.endData = endData;
    }

    public Reservation(LocalDate startData, LocalDate endData, Room roomReservation, Customer customerReservation) {
        this.startData = startData;
        this.endData = endData;
        this.roomReservation = roomReservation;
        this.customerReservation = customerReservation;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getHowLong() {
        return howLong;
    }

    public void setHowLong(int howLong) {
        this.howLong = howLong;
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

    public LocalDate getStartData() {
        return startData;
    }

    public void setStartData(LocalDate startData) {
        this.startData = startData;
    }

    public LocalDate getEndData() {
        return endData;
    }

    public void setEndData(LocalDate endData) {
        this.endData = endData;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
