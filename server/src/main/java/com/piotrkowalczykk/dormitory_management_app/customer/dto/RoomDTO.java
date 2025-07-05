package com.piotrkowalczykk.dormitory_management_app.customer.dto;

import com.piotrkowalczykk.dormitory_management_app.customer.model.Dormitory;

public class RoomDTO {
    private Long id;
    private Long dormitoryId;
    private String dormitoryName;
    private String number;
    private Integer capacity;
    private Integer floor;
    private String type;

    public RoomDTO(Long id, String number, Long dormitoryId, Integer capacity, Integer floor, String type, String dormitoryName) {
        this.id = id;
        this.number = number;
        this.dormitoryId = dormitoryId;
        this.dormitoryName = dormitoryName;
        this.capacity = capacity;
        this.floor = floor;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getDormitory() {
        return dormitoryId;
    }

    public void setDormitory(Long dormitory) {
        this.dormitoryId = dormitory;
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

    public Long getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Long dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }
}
