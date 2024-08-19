package com.example.livestockmanagment.Controller;

import com.example.livestockmanagment.Model.Farm;
import com.example.livestockmanagment.Model.Seller;
import com.example.livestockmanagment.Service.FarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/farm")
@RequiredArgsConstructor
public class FarmController {
    private final FarmService farmService;

    @GetMapping("/get")
    public ResponseEntity getFarm(){
        return ResponseEntity.status(200).body(farmService.getFarm());
    }
    @PostMapping("/add")
    public ResponseEntity addFarm(@Valid @RequestBody Farm farm, Errors errors){
        if(errors.hasErrors()){
            String massege=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        farmService.addFarm(farm);
        return ResponseEntity.status(200).body("is added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateFarm(@PathVariable Integer id,@Valid@RequestBody Farm farm,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        farmService.updateFarm(id,farm);
        return ResponseEntity.status(200).body("is Updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFarm(@PathVariable Integer id){
        farmService.deleteFarm(id);
        return ResponseEntity.status(200).body("is deleted");
    }
   //8:Farms with capacity greater than a certain value
    @GetMapping("/farmg/{sellerId}/{capacity}")
    public ResponseEntity getFarmWithCapacityGreaterValue(@PathVariable Integer sellerId,@PathVariable int capacity){
        return ResponseEntity.status(200).body(farmService.farmCapacityGreater(sellerId,capacity));
    }
//11: the average weight of animals for farm
    @GetMapping("/avarge/{farmId}")
    public ResponseEntity getAvarageWeight(@PathVariable Integer farmId){
        return ResponseEntity.status(200).body(farmService.averageWeight(farmId));

    }
    //14:
    @GetMapping("/loca/{loca}")
    public ResponseEntity getAvarageWeight(@PathVariable String loca){
        return ResponseEntity.status(200).body(farmService.getFarmSpecifcLoca(loca));

    }
   //15:To monitor farms that need to add in the stock
    @GetMapping("/farmstock/{sellerId}")
    public ResponseEntity farmStock(@PathVariable Integer sellerId){
        return ResponseEntity.status(200).body(farmService.farmStock(sellerId));

    }
}
