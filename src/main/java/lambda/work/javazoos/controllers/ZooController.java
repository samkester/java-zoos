package lambda.work.javazoos.controllers;

import lambda.work.javazoos.models.Zoo;
import lambda.work.javazoos.services.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/zoos")
public class ZooController {
    @Autowired
    private ZooService zooService;

    @GetMapping(value = "/zoo", produces = {"application/json"})
    private ResponseEntity<?> getAllZoos()
    {
        return new ResponseEntity<>(zooService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/zoo/{id}", produces = {"application/json"})
    private ResponseEntity<?> getZoo(@PathVariable long id)
    {
        return new ResponseEntity<>(zooService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/zoo", produces = {"application/json"}, consumes = {"application/json"})
    private ResponseEntity<?> postZoo(@Valid @RequestBody Zoo zoo)
    {
        zoo.setZooid(0);
        return new ResponseEntity<>(zooService.saveZoo(zoo), HttpStatus.CREATED);
    }

    @PutMapping(value = "/zoo/{id}", produces = {"application/json"}, consumes = {"application/json"})
    private ResponseEntity<?> putZoo(@Valid @RequestBody Zoo zoo, @PathVariable long id)
    {
        zoo.setZooid(id);
        return new ResponseEntity<>(zooService.saveZoo(zoo), HttpStatus.OK);
    }

    @PatchMapping(value = "/zoo/{id}", produces = {"application/json"}, consumes = {"application/json"})
    private ResponseEntity<?> patchZoo(@RequestBody Zoo zoo, @PathVariable long id)
    {
        zoo.setZooid(id);
        return new ResponseEntity<>(zooService.patchZoo(zoo), HttpStatus.OK);
    }

    @DeleteMapping(value = "/zoo/{id}", produces = {"application/json"})
    private ResponseEntity<?> deleteZoo(@PathVariable long id)
    {
        zooService.deleteZoo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
