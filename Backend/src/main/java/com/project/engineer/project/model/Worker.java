package com.project.engineer.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Worker")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idWorker;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @Column(name = "login")
    private String login;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "responsibilities")
    private String responsibilities;

    @Column(name = "position")
    private String position;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "dateofemployment")
    private Date dateofemployment;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "dateofbirth")
    private Date dateofbirth;

    @Column(name = "salary")
    private int salary;

    @Column(name = "activity")
    private String activity;

    @Column(name = "privileges", columnDefinition ="int default 1 ")
    private int privileges;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "workinghours")
    private String workinghours;

    public Worker(){}

    public Worker(String firstname, String lastname, String login, String password, String email, String responsibilities, String position, Date dateofemployment, Date dateofbirth, int salary, String activity, String pesel, String workinghours, int privileges) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.responsibilities = responsibilities;
        this.position = position;
        this.dateofemployment = dateofemployment;
        this.dateofbirth = dateofbirth;
        this.salary = salary;
        this.activity = activity;
        this.pesel = pesel;
        this.workinghours = workinghours;
        this.privileges = privileges;
    }

    public Worker(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Worker(String login, String password, int privileges) {
        this.login = login;
        this.password = password;
        this.privileges = privileges;
    }

    public long getidWorker() {
        return idWorker;
    }

    public void setidWorker(long idWorker) {
        this.idWorker = idWorker;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getPrivileges() {
        return privileges;
    }

    public void setPrivileges(int privileges) {
        this.privileges = privileges;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDateofemployment() {
        return dateofemployment;
    }

    public void setDateofemployment(Date dateofemployment) {
        this.dateofemployment = dateofemployment;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getWorkinghours() {
        return workinghours;
    }

    public void setWorkinghours(String workinghours) {
        this.workinghours = workinghours;
    }
}
