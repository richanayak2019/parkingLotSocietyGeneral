package com.skillenza.parkinglotjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class ParkingLotController {

    // your code goes here
    ParkingLotRepository repository;

    @Autowired
    ParkingLotController(ParkingLotRepository repository) {
        this.repository = repository;

    }

    @RequestMapping(value = "/parkings/{id}", method = RequestMethod.GET)
    public ParkingLot getParkingLot(@PathVariable("id") long id) {
        ParkingLot p = repository.findById(id);
        if (p == null) {
            throw new ResourceNotFoundException("ParkingLot", String.valueOf(id), null);
        }
        return p;
    }

    @RequestMapping(value = "/parkings", method = RequestMethod.GET)
    public List<ParkingLot> getParkingLots() {
        return repository.findAll();
    }

    @RequestMapping(value = "/parkings", method = RequestMethod.POST)
    public ParkingLot saveParking(@RequestBody ParkingLot p) {


        int amount = p.getParkingAmount();
        int duration = p.getParkingDuration();

        int calculatedCost =0 ;
        if(duration > 60 ){
            calculatedCost= (int)(20 + (duration-60)*0.333);
        }
        else{
            calculatedCost = 20;
        }

        System.out.println(String.format("%d , %d , %d , %d ", amount,duration, p.getLot(),p.getVehicleNumber()  )) ;


        if(calculatedCost!= amount){
            throw new ResourceNotFoundException("Incorrect Amount", "ParkingAmount", amount);
        }
        return repository.save(p);
    }

    @RequestMapping(value = "/parkings/findByLot/{lotId}", method = RequestMethod.GET)
    public ParkingLot getParkingByLot(@PathVariable("lotId") int id) {
        ParkingLot p = repository.findByLot(id);
        if (p == null) {
            throw new ResourceNotFoundException("ParkingLot","lot" ,String.valueOf(id));
        }
        return p;
    }
}

