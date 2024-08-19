package com.example.livestockmanagment.Service;

import com.example.livestockmanagment.Api.ApiExeption;
import com.example.livestockmanagment.Model.Buyer;
import com.example.livestockmanagment.Model.Farm;
import com.example.livestockmanagment.Model.Livestock;
import com.example.livestockmanagment.Repository.BuyerRepository;
import com.example.livestockmanagment.Repository.FarmRepository;
import com.example.livestockmanagment.Repository.LivestockRepository;
import com.example.livestockmanagment.Repository.TranscationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerService {
    private final BuyerRepository buyerRepository;
    private final LivestockRepository livestockRepository;
    private final FarmRepository farmRepository;
    public List<Buyer> getBuyer() {

        return buyerRepository.findAll();
    }
    public void addBuyer(Buyer buyer){
        buyer.setDiscountCode(0);
        buyerRepository.save(buyer);
    }
    public void updateBuyer(Integer id,Buyer buyer){
        Buyer b=buyerRepository.findBuyerById(id);
        if(b==null){
            throw new ApiExeption("not found");
        }
        b.setUsername(buyer.getUsername());
        b.setPassword(buyer.getPassword());
        b.setName(buyer.getName());
        b.setEmail(buyer.getEmail());
        b.setMembership(buyer.getMembership());
        buyerRepository.save(b);

    }

    public void deleteBuyer(Integer id){
        Buyer b=buyerRepository.findBuyerById(id);
        if(b==null){
            throw new ApiExeption("user not found");
        }
        buyerRepository.delete(b);
    }
     //3:Buy livestock
    public void buyLivestock(Integer buyrid,Integer farmId,Integer livid){
        Livestock l=livestockRepository.findLivestockById(livid);
        Buyer b=buyerRepository.findBuyerById(buyrid);
        Farm f=farmRepository.findFarmById(farmId);
        if(l==null || b==null || f==null || l.getStatus().equalsIgnoreCase("Sold")||f.getStock()==0){
            throw new ApiExeption("You can not buy");
        }
        //if member ship is Silver make discount 15%
      if(b.getMembership().equalsIgnoreCase("Silver")){
          l.setPrice(l.getPrice()*0.85);
          l.setStatus("Sold");
          f.setStock(f.getStock()-1);

          //if member ship is Gold make discount 25%
      } else if (b.getMembership().equalsIgnoreCase("Gold")){
          l.setPrice(l.getPrice()*0.75);
          l.setStatus("Sold");
          f.setStock(f.getStock()-1);
      }else {
          l.setPrice(l.getPrice());
          l.setStatus("Sold");
          f.setStock(f.getStock()-1);
      }if(f.getStock()==0){
          f.setStatus("Empty");
        }
     livestockRepository.save(l);
      farmRepository.save(f);

    }

}
