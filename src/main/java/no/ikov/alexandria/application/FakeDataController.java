package no.ikov.alexandria.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fakedata")
public class FakeDataController {

    private final InitData initData;

    public FakeDataController(InitData initData) {
        this.initData = initData;
    }

    @GetMapping
    public ResponseEntity<String> createFakeData(){
        initData.createTestData();
        return ResponseEntity.ok("Test data created");
    }
}