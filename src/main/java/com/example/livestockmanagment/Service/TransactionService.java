package com.example.livestockmanagment.Service;

import com.example.livestockmanagment.Api.ApiExeption;
import com.example.livestockmanagment.Model.*;
import com.example.livestockmanagment.Repository.BuyerRepository;
import com.example.livestockmanagment.Repository.LivestockRepository;
import com.example.livestockmanagment.Repository.SellerRepository;
import com.example.livestockmanagment.Repository.TranscationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TranscationRepository transcationRepository;
    private final SellerRepository sellerRepository;
    private final BuyerRepository buyerRepository;
    private final LivestockRepository livestockRepository;

    public List<Transcation> getTransaction() {
        return transcationRepository.findAll();
    }

    public void addTransaction(Transcation transcation) {
        Seller s = sellerRepository.findSellerById(transcation.getSelleId());
        Buyer b = buyerRepository.findBuyerById(transcation.getBuyerId());
        Livestock l = livestockRepository.findLivestockById(transcation.getLivestockId());
        if (s == null || b == null || l == null || l.getStatus().equalsIgnoreCase("Available")) {
            throw new ApiExeption("can not add ,Because you dont buying or not found seller or buyer or livestock");
        }
        transcation.setDate(LocalDate.now());
        transcation.setStatus("Pending");
        transcationRepository.save(transcation);
    }

    public void updateTransaction(Integer id, Transcation transcation) {
        Transcation t = transcationRepository.findTranscationById(id);
        if (t == null) {
            throw new ApiExeption("not found");
        }
        t.setStatus(transcation.getStatus());
        t.setDate(transcation.getDate());
        transcationRepository.save(transcation);
    }

    public void deleteTransaction(Integer id) {
        Transcation t = transcationRepository.findTranscationById(id);
        if (t == null) {
            throw new ApiExeption("Transaction not found");
        }
        transcationRepository.delete(t);
    }

    //6: discount for buyer if the transaction mor than or equal 3
    public List<Transcation> allTrarnsaction(Integer buyerId) {
        List<Transcation> t = transcationRepository.getTranscationsByBuyerId(buyerId);
        Buyer b = buyerRepository.findBuyerById(buyerId);
        if (t.isEmpty()) {
            throw new ApiExeption("not found");
        }
        if (t.size() >= 3) {
            b.setDiscountCode(b.getDiscountCode() + 1);
            buyerRepository.save(b);
        }
        return t;

    }

    //12:All transactions sold on a specific date
    public List<Transcation> getTransactionByDate(LocalDate date) {
        List<Transcation> t = transcationRepository.getTransByDate(date);
        if (t.isEmpty()) {
            throw new ApiExeption("not found");
        }
        return t;
    }

    //13: Get All transaction complete
    public List<Transcation> getTransactionCom(String status) {
        List<Transcation> t=transcationRepository.TransByStatusComplete(status);
        if (t.isEmpty()) {
            throw new ApiExeption("not found");
        }
        return t;
    }


}