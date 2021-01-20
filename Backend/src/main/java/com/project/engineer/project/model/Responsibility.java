package com.project.engineer.project.model;

import javax.persistence.*;

@Entity
@Table(name = "Responsibility")
public class Responsibility {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idResponsibility;

    @Column(name = "nameResponsibility")
    String nameResponsibility;

    @Column(name = "description")
    String description;

    @Column(name = "responsible")
    String responsible;

    @Column(name = "status")
    String status;

    @Column(name = "idworkerResponsibility")
    long idworkerResponsibility;

    public Responsibility() {
    }

    public Responsibility(String description) {
        this.description = description;
    }

    public Responsibility(String nameResponsibility, String description, String responsible, String status,  long idworkerResponsibility) {
        this.nameResponsibility = nameResponsibility;
        this.description = description;
        this.responsible = responsible;
        this.status = status;
        this.idworkerResponsibility = idworkerResponsibility;
    }

    public long getIdResponsibility() {
        return idResponsibility;
    }

    public void setIdResponsibility(long idResponsibility) {
        this.idResponsibility = idResponsibility;
    }

    public String getNameResponsibility() {
        return nameResponsibility;
    }

    public void setNameResponsibility(String nameResponsibility) {
        this.nameResponsibility = nameResponsibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getIdworkerResponsibility() {
        return idworkerResponsibility;
    }

    public void setIdworkerResponsibility(long idworkerResponsibility) {
        this.idworkerResponsibility = idworkerResponsibility;
    }
}
