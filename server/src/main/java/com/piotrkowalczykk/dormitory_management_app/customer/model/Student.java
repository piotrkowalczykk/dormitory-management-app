package com.piotrkowalczykk.dormitory_management_app.customer.model;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String studentNumber;
    @ManyToOne
    @JoinColumn(name = "academy_id")
    private Academy academy;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Student(String email, String studentNumber, Academy academy, Room room) {
        this.email = email;
        this.studentNumber = studentNumber;
        this.academy = academy;
        this.room = room;
    }

    public Student(){

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

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
