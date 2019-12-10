package com.task.houseManagement;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.houseManagement.controller.HouseController;
import com.task.houseManagement.entity.Address;
import com.task.houseManagement.entity.House;
import com.task.houseManagement.entity.Type;
import com.task.houseManagement.service.HouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HouseController.class)
public class HouseControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    HouseService houseService;


    /**
     * Tests the retrieval of house list
     *
     * @throws Exception if the update operation of a service fails
     */

    @Test
    public void getHouses() throws Exception {
        mockMvc.perform(get("/houses/").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("mo:12345".getBytes())))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
        verify(houseService, times(1)).retrieveAllHouses();
    }

    /**
     * Tests the retrieval of sorted houses as list
     *
     * @throws Exception if the update operation of a service fails
     */

    @Test
    public void getSortedHouses() throws Exception {
        mockMvc.perform(get("/sortedhouses/area").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("mo:12345".getBytes())))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(houseService, times(1)).retrieveAllHousesSorted("area");
    }

    /**
     * Tests the retrieval of a single house by ID.
     *
     * @throws Exception if the update operation of a service fails
     */
    @Test
    public void getHouse() throws Exception {
        mockMvc.perform(get("/house/1").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("mo:12345".getBytes())))
                .andExpect(status().isOk());

        verify(houseService, times(1)).retrieveHouse((long)1);
    }


    /**
     * Tests the deletion of a single house by ID.
     *
     * @throws Exception if the delete operation of the service fails
     */
    @Test
    public void deleteHouse() throws Exception {

        mockMvc.perform(delete("/house/2").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("mo:12345".getBytes())))
                .andExpect(status().isNoContent());

        verify(houseService, times(1)).deleteHouseById((long) 2);
    }

    /**
     * Tests the update of a single house by ID.
     *
     * @throws Exception if the update operation of the service fails
     */
    @Test
    public void updateHouse() throws Exception {
        House savedHouse = getAHouse();
        savedHouse.setPrice("20000000$");

        mockMvc.perform(
                put("/house/" + savedHouse.getId().toString(), savedHouse.getId())
                        .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("mo:12345".getBytes()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(savedHouse)))
                .andExpect(status().isOk());
    }

    /**
     * Tests the addition of a single house by ID.
     *
     * @throws Exception if the update operation of the service fails
     */

    @Test
    public void addMyhouse() throws Exception {
        House house = getANewHouse();
        mockMvc.perform(post("/houses").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("mo:12345".getBytes()))
                .content(asJsonString(house))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }

    @Test
    public void addMyhouseWithMissingStreet() throws Exception {
        House house = getANewHouseWithMissingStreet();
        mockMvc.perform(post("/houses").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("mo:12345".getBytes()))
                .content(asJsonString(house))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private House getAHouse() {
        House house = new House();
        house.setId((long) 1);
        house.setArea(100);
        house.setPrice("300000$");
        house.setType(Type.APARTMENT);
        house.setNewHouse(false);
        house.setAddress(new Address("Fromund",5, 73225, "Hanover"));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date constructionDate = cal.getTime();
        house.setConstructionDate(constructionDate);

        return house;
    }


    private House getANewHouse() {
        House house = new House();
        house.setArea(200);
        house.setPrice("300000$");
        house.setType(Type.BUNGALOW);
        house.setNewHouse(false);
        house.setAddress(new Address("Fromund",13, 32115, "Berlin"));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date constructionDate = cal.getTime();
        house.setConstructionDate(constructionDate);

        return house;
    }

    private House getANewHouseWithMissingStreet() {
        House house = new House();
        house.setArea(200);
        house.setPrice("300000$");
        house.setType(Type.BUNGALOW);
        house.setNewHouse(false);
        house.setAddress(new Address(" ",13, 32115, "Berlin"));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date constructionDate = cal.getTime();
        house.setConstructionDate(constructionDate);

        return house;
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch ( Exception e ) {
            throw new RuntimeException(e);
        }
    }
}