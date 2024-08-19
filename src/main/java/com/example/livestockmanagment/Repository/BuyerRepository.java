package com.example.livestockmanagment.Repository;

import com.example.livestockmanagment.Model.Buyer;
import com.example.livestockmanagment.Model.Livestock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Integer> {
    Buyer findBuyerById(Integer id);

}
