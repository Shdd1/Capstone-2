package com.example.livestockmanagment.Controller;

import com.example.livestockmanagment.Model.Seller;
import com.example.livestockmanagment.Service.SellerService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seller")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;
    @GetMapping("/get")
    public ResponseEntity getSeller(){
        return ResponseEntity.status(200).body(sellerService.getSeller());
    }
    @PostMapping("/add")
    public ResponseEntity addSeller(@Valid @RequestBody Seller seller, Errors errors){
        if(errors.hasErrors()){
            String massege=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massege);
        }
        sellerService.addSeller(seller);
        return ResponseEntity.status(200).body("is added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateSeller(@PathVariable Integer id,@Valid@RequestBody Seller seller,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        sellerService.updateSeller(id,seller);
        return ResponseEntity.status(200).body("is Updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSeller(@PathVariable Integer id){
        sellerService.deleteSeller(id);
        return ResponseEntity.status(200).body("is deleted");
    }

    //4:change status of transaction to complete
    @PutMapping("/change/{sellerId}/{transId}")
    public ResponseEntity changeStatus(@PathVariable Integer sellerId,@PathVariable Integer transId){
        sellerService.changeStatus(sellerId,transId);
        return ResponseEntity.status(200).body("is updated");
    }
    //5:Transfer of ownership
    @PutMapping("/owner/{sellerId}/{liveId}/{tranId}")
    public ResponseEntity owner(@PathVariable Integer sellerId,@PathVariable Integer liveId,@PathVariable Integer tranId){
        sellerService.TransferOfOwnership(sellerId,liveId,tranId);
        return ResponseEntity.status(200).body("Ownership has been transferred successfully");
    }
}
