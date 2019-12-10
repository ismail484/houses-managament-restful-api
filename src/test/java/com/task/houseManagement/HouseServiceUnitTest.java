package com.task.houseManagement;


import com.task.houseManagement.entity.Address;
import com.task.houseManagement.entity.House;
import com.task.houseManagement.entity.Type;
import com.task.houseManagement.service.HouseNotFoundException;
import com.task.houseManagement.service.HouseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseServiceUnitTest {

    @Autowired
    HouseService houseService;



    /**
     * Tests the retrieval of house list
     *
     * @throws Exception if the update operation of a service fails
     */

    @Test
    public void getHouses() throws Exception {
        this.saveCustomHouse();
        Assert.assertNotNull(houseService.retrieveAllHouses());
    }

    /**
     * Tests the retrieval of sorted houses as list
     *
     * @throws Exception if the update operation of a service fails
     */


    @Test
    public void getSortedHouses() throws Exception {
        this.saveCustomHouse();
        List<House> houseList = (List<House>) houseService.retrieveAllHousesSorted(("area"));
        Assertions.assertNotNull(houseList);

    }

    /**
     * Tests the retrieval of a single house by ID.
     *
     * @throws Exception if the update operation of a service fails
     */
    @Test
    public void getHouse() throws Exception {
        House savedHouse = this.saveCustomHouse();
        Optional<House> houseDatabase = Optional.ofNullable(houseService.retrieveHouse(savedHouse.getId()));

        assertEquals(houseDatabase.get().getPrice(), savedHouse.getPrice());
        assertEquals(houseDatabase.get().getArea(), savedHouse.getArea());
        assertEquals(houseDatabase.get().getType(), savedHouse.getType());
        assertEquals(houseDatabase.get().isNewHouse(), savedHouse.isNewHouse());


    }

    /**
     * Tests the retrieval of a single house by ID.
     *
     * @throws Exception if the update operation of a service fails
     */
    @Test(expected = HouseNotFoundException.class)
    public void getHouseWithProblems() throws HouseNotFoundException {

        houseService.retrieveHouse((long) 10);
    }

    @Test(expected = HouseNotFoundException.class)
    public void deleteHouseWithProblems() throws HouseNotFoundException {

        Optional<House>house = houseService.deleteHouseById((long) 10);

        houseService.deleteHouseById((long) 10);
    }


    @Test
    public void addHouse() throws Exception {

        House house = new House();
        house.setArea(350);
        house.setPrice("1300000$");
        house.setType(Type.APARTMENT);
        house.setNewHouse(false);
        house.setAddress(new Address("Nina",5, 30240, "Hamburg"));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 5);
        Date constructionDate = cal.getTime();
        house.setConstructionDate(constructionDate);
        House houseSaved = houseService.saveHouse(house);
        long id = houseSaved.getId();

        Optional<House> houseFromDataBase = Optional.ofNullable(houseService.retrieveHouse(id));

        assertEquals(houseFromDataBase.get().getPrice(), house.getPrice());
        assertEquals(houseFromDataBase.get().getArea(), house.getArea());
        assertEquals(houseFromDataBase.get().getType(), house.getType());
        assertEquals(houseFromDataBase.get().isNewHouse(), house.isNewHouse());
    }

    @Test(expected = ConstraintViolationException.class)
    public void addHouseWithMissingStreet() throws Exception {

        House house = new House();
        house.setArea(350);
        house.setPrice("1300000$");
        house.setType(Type.APARTMENT);
        house.setNewHouse(false);
        house.setAddress(new Address(" ",5, 30240, "Hamburg"));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 5);
        Date constructionDate = cal.getTime();
        house.setConstructionDate(constructionDate);
        House houseSaved = houseService.saveHouse(house);
        long id = houseSaved.getId();

        Optional<House> houseFromDataBase = Optional.ofNullable(houseService.retrieveHouse(id));
    }

    @Test
    public void updatehouse() throws Exception {
        House savedHouse = this.saveCustomHouse();

        savedHouse.setPrice("1800000$");
        houseService.saveHouse(savedHouse);

        Optional<House> retrievedHouse = Optional.ofNullable(houseService.retrieveHouse(savedHouse.getId()));

        assertEquals(savedHouse.getPrice(), retrievedHouse.get().getPrice());

        this.deleteLasthouse(savedHouse.getId());
    }

    public void deletehouse()  {
        House savedHouse = this.saveCustomHouse();
        houseService.deleteHouseById(savedHouse.getId());
        Optional<House> house = Optional.ofNullable(houseService.retrieveHouse(savedHouse.getId()));

        assertEquals(house, Optional.empty());
    }


    private void deleteLasthouse(long id) {
        houseService.deleteHouseById(id);
    }

    private House saveCustomHouse() {
        House house = new House();
        house.setArea(100);
        house.setPrice("300000$");
        house.setType(Type.CHALET);
        house.setNewHouse(false);
        house.setAddress(new Address("Fromund",42, 61577, "Stuttgart"));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date constructionDate = cal.getTime();
        house.setConstructionDate(constructionDate);
        return houseService.saveHouse(house);
    }

}
