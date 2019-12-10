package com.task.houseManagement.service;

import com.task.houseManagement.entity.House;
import com.task.houseManagement.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultHouseService implements HouseService{

    @Autowired
    HouseRepository hauseRepository;


    @Override
    public List<House> retrieveAllHouses() {
        return (List<House>) hauseRepository.findAll();
    }

    @Override
    public House retrieveHouse(long id) {
        Optional<House> house = hauseRepository.findById(id);
        House myHouse = house.orElseThrow(() -> new HouseNotFoundException("House NOT Found Error!...please try again with another house"));
        return myHouse;
    }

    @Override
    public House retrieveHouseByPrice(String price) {
        Optional<House> house = hauseRepository.findByPrice(price);
        House myHouse = house.orElseThrow(() -> new HouseNotFoundException("House NOT Found Error!...please try again with another house"));
        return myHouse;
    }

    @Override
    public House saveHouse(House house) {
        return hauseRepository.save(house);
    }

    @Override
    public Optional<House> deleteHouseById(long id) {

        Optional<House> house = hauseRepository.findById(id);

        if (!house.isPresent()) {
            throw new HouseNotFoundException("House NOT Found Error!, House is already deleted!");
        }
        hauseRepository.deleteById(id);

        return house;
    }

    @Override
    public List<House> retrieveAllHousesSorted(String field) {
        List<House> sortedHouses = (List<House>) hauseRepository.findAll(Sort.by(field));
        return sortedHouses;
    }
}
