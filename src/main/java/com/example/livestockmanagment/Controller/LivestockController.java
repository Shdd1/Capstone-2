package com.example.livestockmanagment.Controller;

import com.example.livestockmanagment.Model.Livestock;
import com.example.livestockmanagment.Model.Seller;
import com.example.livestockmanagment.Service.LivestockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/live")
@RequiredArgsConstructor
public class LivestockController {
    private final LivestockService livestockService;
    @GetMapping("/get")
    public ResponseEntity getLivestock(){
        return ResponseEntity.status(200).body(livestockService.getLivestock());
    }
    @PostMapping("/add")
    public ResponseEntity addLivestock(@Valid @RequestBody Livestock livestock, Errors errors){
        if(errors.hasErrors()){
            String massege=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        livestockService.addLivestock(livestock);
        return ResponseEntity.status(200).body("is added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateLivestock(@PathVariable Integer id, @Valid@RequestBody Livestock livestock, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        livestockService.updateLivestock(id,livestock);
        return ResponseEntity.status(200).body("is Updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteLivestock(@PathVariable Integer id){
        livestockService.deleteLivestock(id);
        return ResponseEntity.status(200).body("is deleted");
    }
    //1:calculate the price for camels
    @PutMapping("/getprice/{id}")
    public ResponseEntity getPriceC(@PathVariable Integer id){
        livestockService.CalculateThePriceOfCamels(id);
        return ResponseEntity.status(200).body("is updated");
    }
    //2:calculate the price for Horses
    @PutMapping("/getpriceh/{id}")
    public ResponseEntity getPriceH(@PathVariable Integer id){
        livestockService.CalculateThePriceOfHorses(id);
        return ResponseEntity.status(200).body("is updated");
    }

    //7:get livestock in the farm by Status(To know what is available)
        @GetMapping("/livestock/{farmId}/{status}")
       public ResponseEntity getLivestockAvailable(@PathVariable Integer farmId,@PathVariable String status){
          return ResponseEntity.status(200).body(livestockService.getLiveAvailable(farmId,status));
       }
    //9:get livestock between two age
    @GetMapping("/age/{type}/{minAge}/{maxAge}")
    public ResponseEntity getLivestockAge(@PathVariable String type,@PathVariable int minAge,@PathVariable int maxAge){
        return ResponseEntity.status(200).body(livestockService.livestockBetweenTwoAge(type,minAge,maxAge));
    }
    //10:Order price
    @GetMapping("/agep/{type}")
    public ResponseEntity getLivestockPrice(@PathVariable String type){
        return ResponseEntity.status(200).body(livestockService.orderByPrice(type));
    }
    //15:
//    @GetMapping("/enddate/{farmId}")
//    public ResponseEntity getEndDate(@PathVariable Integer farmId){
//        return ResponseEntity.status(200).body(livestockService.getEndDate(farmId));
//    }


}
