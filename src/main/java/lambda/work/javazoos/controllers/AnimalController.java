package lambda.work.javazoos.controllers;

import lambda.work.javazoos.services.AnimalService;
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
    public ResponseEntity<?> getAnimalCount()
    {
        List animals = animalService.getAll();
        return new ResponseEntity<>(animals.size(), HttpStatus.OK);
    }
}
