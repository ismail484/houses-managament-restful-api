package com.task.houseManagement;

import com.task.houseManagement.entity.Address;
import com.task.houseManagement.entity.House;
import com.task.houseManagement.entity.Type;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HouseManagementApplicationTests {

    private static final String SERVER = "http://localhost:";
    private static final String GET_ALL_HOUSES_URL = "/houses";
    private static final String GET_ALL_SORTED_HOUSES_URL = "/sortedhouses/{sortedBy}";
    private static final String GET_HOUSE_ENDPOINT_URL = "/house/{id}";
    private static final String Add_HOUSE_ENDPOINT_URL = "/addhouse";
    private static final String UPDATE_HOUSE_ENDPOINT_URL = "/house/{id}";
    private static final String DELETE_HOUSE_ENDPOINT_URL = "/house/{id}";


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    public HouseManagementApplicationTests() {
    }

    @org.junit.Test
    public void getHouses() {

        ResponseEntity<List> response =
                this.restTemplate.withBasicAuth("mo", "12345").getForEntity(SERVER + port + GET_ALL_HOUSES_URL, List.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @org.junit.Test
    public void getHouse() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");


        ResponseEntity<House> response =
                this.restTemplate.withBasicAuth("mo", "12345").getForEntity(SERVER + port + GET_HOUSE_ENDPOINT_URL, House.class, params);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }


    @org.junit.Test
    public void addHouse() {
        House house = getANewHouse();
        ResponseEntity<House> response = this.restTemplate.withBasicAuth("mo", "12345").postForEntity(SERVER + port + Add_HOUSE_ENDPOINT_URL, house, House.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @org.junit.Test
    public void addHouseWithMissingStreet() {
        House house = getANewHouseWithMissingStreet();
        ResponseEntity<House> response = this.restTemplate.withBasicAuth("mo", "12345").postForEntity(SERVER + port + Add_HOUSE_ENDPOINT_URL, house, House.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
    }

    @org.junit.Test
    public void updateHouse() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");
        House house = getAHouse();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<House> requestEntity = new HttpEntity<House>(house, headers);
        ResponseEntity<House> response = restTemplate.withBasicAuth("mo", "12345").exchange(SERVER + port + UPDATE_HOUSE_ENDPOINT_URL, HttpMethod.PUT, requestEntity, House.class, params);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }



    @org.junit.Test
    public void SortedHouses() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("sortedBy", "area");
        ResponseEntity<List> response =
                this.restTemplate.withBasicAuth("mo", "12345").getForEntity(SERVER + port + GET_ALL_SORTED_HOUSES_URL, List.class, params);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @org.junit.Test
    public void deleteHouse() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "2");
        House house = getAHouse();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<House> requestEntity = new HttpEntity<House>(house, headers);
        ResponseEntity<House> response = restTemplate.withBasicAuth("mo", "12345").exchange(SERVER + port + DELETE_HOUSE_ENDPOINT_URL, HttpMethod.DELETE, requestEntity, House.class, params);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));

    }


    private House getAHouse() {
        House house = new House();
        house.setArea(200);
        house.setPrice("300000$");
        house.setType(Type.SINGLE_FAMILY_DETACHED);
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
        house.setType(Type.SINGLE_FAMILY_DETACHED);
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

}
