package com.piotrkowalczykk.dormitory_management_app.customer.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name = "dormitory_id")
    private Dormitory dormitory;
    private String number;
    private Integer capacity;
    private Integer floor;
    private String type;
    @OneToMany(mappedBy = "room")
    private List<Student> students;

    public Room(Dormitory dormitory, String number, Integer capacity, Integer floor, String type) {
        this.dormitory = dormitory;
        this.number = number;
        this.capacity = capacity;
        this.floor = floor;
        this.type = type;
    }

    public Room() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
