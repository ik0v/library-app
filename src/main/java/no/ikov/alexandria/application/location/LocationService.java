package no.ikov.alexandria.application.location;

import no.ikov.alexandria.exceptions.LocationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepo locationRepo;

    @Autowired
    public LocationService(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    public List<Location> findAll() {
        return locationRepo.findAll();
    }

    public Location findById(long id) {
        try {
            return locationRepo.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new LocationNotFoundException(id);
        }
    }

    public Location save(Location location) {
        return locationRepo.save(location);
    }

    public void deleteById(long id) {
        locationRepo.deleteById(id);
    }
}
