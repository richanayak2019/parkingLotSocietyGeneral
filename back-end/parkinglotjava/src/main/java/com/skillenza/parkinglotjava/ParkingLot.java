package com.skillenza.parkinglotjava;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "parkinglots")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class ParkingLot {
	
    //your code goes here
    /*
     lot INT NOT NULL,
    parking_amount INT NOT NULL,
    parking_duration INT NOT NULL,
    updated_at  DATETIME NOT NULL,
    vehicle_number INT NOT NULL,
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="lot", nullable = false)
    private int lot;
    @Column(name="parking_amount" , nullable = false)
    private int parkingAmount;
    @Column(name="parking_duration", nullable = false)
    private int parkingDuration;
    @Column(name="vehicle_number", nullable = false)
    private int vehicleNumber;

    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    private LocalDateTime updateDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {


        this.lot = lot;
    }

    public int getParkingAmount() {
        return parkingAmount;
    }

    public void setParkingAmount(int parkingAmount) {
        this.parkingAmount = parkingAmount;
    }

    public int getParkingDuration() {
        return parkingDuration;
    }

    public void setParkingDuration(int parkingDuration) {
        this.parkingDuration = parkingDuration;
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(int vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
