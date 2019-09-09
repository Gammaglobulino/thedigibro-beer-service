package com.thedigibro.thedigibrobeerservice.web.controller;

import com.thedigibro.thedigibrobeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId")UUID beerId){
        //todo real impl of retrieving an obj
        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody BeerDto beerDto){
        //todo implementing saving an obj
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId")UUID beerId,@RequestBody BeerDto beerDto){
        //todo implementing update an object
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
