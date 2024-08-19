package com.example.livestockmanagment.Repository;

import com.example.livestockmanagment.Model.Transcation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TranscationRepository extends JpaRepository<Transcation,Integer> {
    Transcation findTranscationById(Integer id);
    @Query("select t from Transcation t where t.buyerId=?1 ")
    List<Transcation> getTranscationsByBuyerId(Integer buyerId);
    @Query("select t from Transcation t where t.date=?1")
    List<Transcation> getTransByDate(LocalDate date);
    @Query("select t from Transcation t where t.status=?1")
    List<Transcation> TransByStatusComplete(String status);

}
