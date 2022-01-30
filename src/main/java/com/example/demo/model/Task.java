package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private double id;

    @Column(name = "text")
    private String text;

    @Column(name = "day")
    private String day;

    @Column(name = "reminder")
    private boolean reminder;

    public Task() {
    }

    public Task(double id, String text, String day, boolean reminder) {
        this.id = id;
        this.text = text;
        this.day = day;
        this.reminder = reminder;
    }

    public double getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getDay() {
        return day;
    }

    public boolean isReminder() {
        return reminder;
    }

    public void setId(double id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", day='" + day + '\'' +
                ", reminder=" + reminder +
                '}';
    }
}
