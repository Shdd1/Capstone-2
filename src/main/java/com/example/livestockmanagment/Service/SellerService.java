package com.example.livestockmanagment.Service;

import com.example.livestockmanagment.Api.ApiExeption;
import com.example.livestockmanagment.Model.Buyer;
import com.example.livestockmanagment.Model.Livestock;
import com.example.livestockmanagment.Model.Seller;
import com.example.livestockmanagment.Model.Transcation;
import com.example.livestockmanagment.Repository.BuyerRepository;
import com.example.livestockmanagment.Repository.LivestockRepository;
import com.example.livestockmanagment.Repository.SellerRepository;
import com.example.livestockmanagment.Repository.TranscationRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;
    private final TranscationRepository transcationRepository;
    private final LivestockRepository livestockRepository;

    public List<Seller> getSeller() {
        return sellerRepository.findAll();
    }
    public void addSeller(Seller seller){

        sellerRepository.save(seller);
    }
    public void updateSeller(Integer id,Seller seller){
        Seller s=sellerRepository.findSellerById(id);
        if(s==null){
            throw new ApiExeption("not found");
        }
        s.setUsername(seller.getUsername());
        s.setPassword(seller.getPassword());
        s.setName(seller.getName());
        s.setEmail(seller.getEmail());
        sellerRepository.save(s);

    }

    public void deleteSeller(Integer id){
        Seller s=sellerRepository.findSellerById(id);
        if(s==null){
            throw new ApiExeption("user not found");
        }
        sellerRepository.delete(s);
    }
    //4:change status of transaction to complete
    public void changeStatus( Integer sellerId,Integer transId){
      Transcation t=transcationRepository.findTranscationById(transId);
      Seller s=sellerRepository.findSellerById(sellerId);
        if(t==null|| s==null){
            throw  new ApiExeption(" not found");
        }
         t.setStatus("Complete");
        transcationRepository.save(t);
    }

//5:Transfer of ownership
    public void  TransferOfOwnership(Integer sellerId, Integer liveId ,Integer tranId){
       Livestock l=livestockRepository.findLivestockById(liveId);
       Seller s=sellerRepository.findSellerById(sellerId);
       Transcation t=transcationRepository.findTranscationById(tranId);
       if(l==null||s==null||l.getOwner()!=sellerId ||t.getStatus().equalsIgnoreCase("Pending")){
           throw new ApiExeption("not found");
       }
       l.setOwner(t.getBuyerId());
      livestockRepository.save(l);

    }


}
