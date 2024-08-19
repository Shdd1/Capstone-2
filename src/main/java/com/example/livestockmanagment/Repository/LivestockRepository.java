package com.example.livestockmanagment.Repository;

import com.example.livestockmanagment.Model.Livestock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LivestockRepository extends JpaRepository<Livestock,Integer> {

    Livestock findLivestockById(Integer id);

    @Query("select l from Livestock l where l.farmId=?1 and l.status=?2")
   List<Livestock> findLivestockByFarmIdAndStatus(Integer farmId,String status);
    @Query("select l from Livestock l where l.livestockType=?1 and  l.age>=?2 and l.age<=?3")
    List<Livestock> findLivestockByLivestockTypeAndAge(String type,int ageMine,int ageMax);
    @Query("select l from Livestock l where l.livestockType=?1 order by l.price")
    List<Livestock> findLivestockByOrderPrice(String type);



}
