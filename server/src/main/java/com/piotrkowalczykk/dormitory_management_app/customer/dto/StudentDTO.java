package com.piotrkowalczykk.dormitory_management_app.customer.dto;

public class StudentDTO {
    private Long id;
    private String email;
    private String studentNumber;
    private Long dormitoryId;
    private String dormitoryName;
    private Long roomId;
    private String roomNumber;

    public StudentDTO(Long id, String email, String studentNumber, Long dormitoryId, String dormitoryName, Long roomId, String roomNumber) {
        this.id = id;
        this.email = email;
        this.studentNumber = studentNumber;
        this.dormitoryId = dormitoryId;
        this.dormitoryName = dormitoryName;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
