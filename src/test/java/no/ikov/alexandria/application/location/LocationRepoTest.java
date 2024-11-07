package no.ikov.alexandria.application.location;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LocationRepoTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    LocationRepo locationRepo;

    @Test
    void connectionEstablished(){
        assert postgres.isCreated();
        assert postgres.isRunning();
    }

    @Test
    void shouldInsertLocation(){
        var location = locationRepo.save(new Location("Shelf 100"));
        assert locationRepo.findById(location.getLocation_id()).isPresent();
    }

    @Test
    void shouldUpdateLocation(){
        var location = locationRepo.save(new Location("Shelf 100"));
        location.setShelf("Shelf 224");
        locationRepo.save(location);
        assert locationRepo.findById(location.getLocation_id()).get().getShelf().equals("Shelf 224");
    }

    @Test
    void shouldDeleteLocation(){
        var location = locationRepo.save(new Location("Shelf 100"));
        locationRepo.delete(location);
        assert locationRepo.findById(location.getLocation_id()).isEmpty();
    }

}