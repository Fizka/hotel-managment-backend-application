package com.project.engineer.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idComment;

    @Column(name = "mark")
    private int mark;

    @Column(name = "textValue")
    private String textValue;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "roomId",
            nullable = true
    )
    @JsonIgnore
    private Room roomComment;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "customerId",
            nullable = true
    )
    @JsonIgnore
    private Customer customerComment;

    public Comment() {
    }

    public Comment(int mark, String textValue, Room roomComment, Customer customerComment) {
        this.mark = mark;
        this.textValue = textValue;
        this.roomComment = roomComment;
        this.customerComment = customerComment;
    }

    public long getIdComment() {
        return idComment;
    }

    public void setIdComment(long idComment) {
        this.idComment = idComment;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public Room getRoomComment() {
        return roomComment;
    }

    public void setRoomComment(Room roomComment) {
        this.roomComment = roomComment;
    }

    public Customer getCustomerComment() {
        return customerComment;
    }

    public void setCustomerComment(Customer customerComment) {
        this.customerComment = customerComment;
    }
}
