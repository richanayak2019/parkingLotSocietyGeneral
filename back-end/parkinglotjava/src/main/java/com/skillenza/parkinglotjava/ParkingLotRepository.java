package com.skillenza.parkinglotjava;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long>{
    List<ParkingLot> findAll();
    ParkingLot findById(long id);
    ParkingLot save(ParkingLot lot);
    ParkingLot findByLot(int lot);
}
