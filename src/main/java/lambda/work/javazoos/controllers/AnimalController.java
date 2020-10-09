package lambda.work.javazoos.controllers;

import lambda.work.javazoos.services.AnimalService;
import lambda.work.javazoos.views.AnimalCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @GetMapping(value = "/count", produces = {"application/json"})
    public ResponseEntity<?> getAnimalsByCount()
    {
        List<AnimalCount> animalCounts = animalService.getAnimalsByCount();
        return new ResponseEntity<>(animalCounts, HttpStatus.OK);
    }
}
