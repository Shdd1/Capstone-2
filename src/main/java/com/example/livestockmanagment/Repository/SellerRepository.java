package com.example.livestockmanagment.Repository;

import com.example.livestockmanagment.Model.Seller;
import com.example.livestockmanagment.Model.Transcation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Seller findSellerById(Integer id);

}
