package com.task.houseManagement.controller;

import com.task.houseManagement.entity.House;
import com.task.houseManagement.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HouseController {

    private HouseService houseService;

    @Autowired
    public void seHouseService(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/houses")
    public ResponseEntity<List<House>> getAllHouses() {
        List<House> houses = houseService.retrieveAllHouses();

        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

    @GetMapping("/house/{id}")
    public ResponseEntity<House> getHouseByID(@PathVariable Long id) {
        House house = houseService.retrieveHouse(id);

        return new ResponseEntity<>(house, HttpStatus.OK);
    }

    @GetMapping("/house/price/{price}")
    public ResponseEntity<House> getHouseByPrice(@PathVariable String price) {
        House house = houseService.retrieveHouseByPrice(price);

        return new ResponseEntity<>(house, HttpStatus.OK);
    }

    @PutMapping(path = "/house/{id}", produces = {"application/json"})
    public ResponseEntity<House> updateHouse(@PathVariable Long id, @Valid @RequestBody House house) {
        //Set the id of the passed house object to the passed `id`
        house.setId(id);
        House res = houseService.saveHouse(house);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/addhouse")
    public ResponseEntity<House> addHouse(@Valid @RequestBody House house) {
        House res = houseService.saveHouse(house);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/house/{id}")
    public ResponseEntity<House> deleteHouseById(@PathVariable Long id) {

        houseService.deleteHouseById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sortedhouses/{sortedBy}")
    public ResponseEntity<List<House>> getAllSortedHouses(@PathVariable String sortedBy) {
        List<House> houses = houseService.retrieveAllHousesSorted(sortedBy);
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }
}
