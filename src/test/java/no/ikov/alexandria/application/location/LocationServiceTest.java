package no.ikov.alexandria.application.location;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LocationServiceTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    LocationService locationService;

    @Test
    @Order(1)
    void setup(){
        for (int i = 0; i < 10; i++) {
            locationService.save(new Location("Shelf " + i));
        }
    }

    @Test
    @Order(2)
    void getLocations() {
        List<Location> locations = locationService.findAll();
        assert locations.size() == 10;
    }

    @Test
    @Order(3)
    void getLocationById() {
        var location = locationService.findById(1);
        assert location != null;
        assert location.getShelf().equals("Shelf 0");
    }

    @Test
    @Order(4)
    void saveLocation() {
        assert locationService.save(new Location("Shelf 110")).getShelf().equals("Shelf 110");
    }

    @Test
    @Order(5)
    void deleteLocationById() {
        locationService.deleteById(1);
        assert locationService.findAll().size() == 10;
    }
}