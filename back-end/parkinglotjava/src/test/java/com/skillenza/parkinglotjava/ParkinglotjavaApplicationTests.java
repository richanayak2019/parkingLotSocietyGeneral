package com.skillenza.parkinglotjava;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkinglotjavaApplicationTests {


	
    //your test goes here

    @Test
    public void testSavePatient() {
        ParkingLotRepository parkingRepository = mock(ParkingLotRepository.class);
        ParkingLot p = new ParkingLot();
        p.setLot(1);
        p.setVehicleNumber(123);
        p.setParkingDuration(60);
        p.setParkingAmount(20);

        ParkingLotController controller = new ParkingLotController(parkingRepository);
        when(parkingRepository.save(p)).thenReturn(p);
        ParkingLot finalResult = controller.saveParking(p);
        assertNotNull(finalResult);
        assertEquals(finalResult, p);
    }

    @Test
    public void testGetPatient() {
        ParkingLotRepository parkingRepository = mock(ParkingLotRepository.class);
        ParkingLot p = new ParkingLot();
        p.setLot(1);
        p.setVehicleNumber(123);
        p.setParkingDuration(60);
        p.setParkingAmount(20);

        Optional<ParkingLot> optionalP = Optional.of(p);
        ParkingLotController controller = new ParkingLotController(parkingRepository);

        when(parkingRepository.save(p)).thenReturn(p);
        when(parkingRepository.findByLot(p.getLot())).thenReturn(p);
        controller.saveParking(p);
        ParkingLot finalResult = controller.getParkingByLot(1);
        assertNotNull(finalResult);
        assertEquals(finalResult.getLot(), p.getLot());
    }


}
