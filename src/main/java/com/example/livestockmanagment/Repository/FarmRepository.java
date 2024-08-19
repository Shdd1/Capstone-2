package com.example.livestockmanagment.Repository;

import com.example.livestockmanagment.Model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm,Integer> {
    Farm findFarmById(Integer id);
   @Query("select f from Farm f where f.sellerId=?1 and f.capacity>?2")
    List<Farm> findFarmBySellerIdAndAndCapacity(Integer sellerId,int capacity);

@Query("select AVG (l.weight) from Livestock l WHERE l.farmId=?1")
Double findAverageWeightByFarmId(Integer farmId);

@Query("select f from Farm f where f.location=?1")
List<Farm> findFarmByLocationAAndOrderByCapacity(String location);
@Query("SELECT f FROM Farm f WHERE f.stock = 0 and f.sellerId=?1")
List<Farm> findFarmStock(Integer sellerId);




}
