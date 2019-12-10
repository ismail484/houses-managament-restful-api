package com.task.houseManagement.service;

import com.task.houseManagement.entity.House;

import java.util.List;
import java.util.Optional;

public interface HouseService {

    List<House> retrieveAllHouses();

    House retrieveHouse(long id);

    House retrieveHouseByPrice(String price);

    House saveHouse(House house);

    Optional<House> deleteHouseById(long id);

    List<House> retrieveAllHousesSorted(String field);
}
