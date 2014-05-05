package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
public class Passenger implements Serializable {
    private int passengerId;
    private String firstName;
    private String lastName;
    private Date birthDate;

    @Id
    @Column(name = "passengerId")
    @GeneratedValue
    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "birthDate")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        //if (passengerId != passenger.passengerId) return false;
        if (!firstName.equals(passenger.firstName)) return false;
        if (!birthDate.equals(passenger.birthDate)) return false;
        return lastName.equals(passenger.lastName);
    }

    @Override
    public int hashCode() {
        return passengerId;
    }
}
