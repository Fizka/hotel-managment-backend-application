package com.project.engineer.project.model;

import javax.persistence.*;

@Entity
@Table(name = "Room")
public class Room {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idRoom;

    @Column(name = "floor")
    private int floor;

    @Column(name = "numberRM")
    private int numberRM;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @Column(name = "maxCapacity")
    private int maxCapacity;

    @Column(name = "title")
    private String title;

    public Room() {
    }

    public Room(int floor, int numberRM, int price, String description, int maxCapacity, String title) {
        this.floor = floor;
        this.numberRM = numberRM;
        this.price = price;
        this.description = description;
        this.maxCapacity = maxCapacity;
        this.title = title;
    }

    public Room(int floor, int price, String description, int maxCapacity, String title) {
        this.floor = floor;
        this.price = price;
        this.description = description;
        this.maxCapacity = maxCapacity;
        this.title = title;
    }

    public long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(long idRoom) {
        this.idRoom = idRoom;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumberRM() {
        return numberRM;
    }

    public void setNumberRM(int numberRM) {
        this.numberRM = numberRM;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
