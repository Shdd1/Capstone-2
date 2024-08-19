package com.example.livestockmanagment.Service;

import com.example.livestockmanagment.Api.ApiExeption;
import com.example.livestockmanagment.Model.Buyer;
import com.example.livestockmanagment.Model.Farm;
import com.example.livestockmanagment.Model.Livestock;
import com.example.livestockmanagment.Model.Seller;
import com.example.livestockmanagment.Repository.FarmRepository;
import com.example.livestockmanagment.Repository.LivestockRepository;
import com.example.livestockmanagment.Repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;
    private final SellerRepository sellerRepository;
    private final LivestockRepository livestockRepository;

    public List<Farm> getFarm() {

        return farmRepository.findAll();
    }
    public void addFarm(Farm farm){
        Seller s=sellerRepository.findSellerById(farm.getSellerId());
        if(s==null){
            throw new ApiExeption("not found seller can not add farm ");
        }
        farm.setStatus("Empty");
        farm.setStock(0);
        farmRepository.save(farm);
    }
    public void updateFarm(Integer id,Farm farm){
        Farm f=farmRepository.findFarmById(id);
        if(f==null){
            throw new ApiExeption("not found");
        }
        f.setStatus(farm.getStatus());
        f.setName(farm.getName());
        f.setLocation(farm.getLocation());
        f.setStock(farm.getStock());
        f.setCapacity(farm.getCapacity());
        farmRepository.save(f);
    }

    public void deleteFarm(Integer id){
        Farm f=farmRepository.findFarmById(id);
        if(f==null){
            throw new ApiExeption("user not found");
        }
        farmRepository.delete(f);
    }
    //8:Farms with capacity greater than a certain value
    public List<Farm> farmCapacityGreater(Integer sellerId,int capacity ){
        List<Farm> f=farmRepository.findFarmBySellerIdAndAndCapacity(sellerId,capacity);
        if(f.isEmpty()){
            throw new ApiExeption("not found or you do not have farm greater than this capacity");
        }
        return f;
    }
    //11: the average weight of animals for farm
    public double averageWeight(Integer farmId){
        double f=farmRepository.findAverageWeightByFarmId(farmId);
        if(f==0){
            throw new ApiExeption("not fond");
        }
        return f;
    }
    //14:list farm from specific location and Order capacity
    public List<Farm> getFarmSpecifcLoca(String loca){
        List<Farm> f=farmRepository.findFarmByLocationAAndOrderByCapacity(loca);
        if(f.isEmpty()){
            throw new ApiExeption("not found ");
        }
        return f;
    }
    //15:To monitor farms that need to add in the stock
    public List<Farm> farmStock(Integer sellerId){
        List<Farm> l=farmRepository.findFarmStock(sellerId);
        if(l.isEmpty()){
            throw new ApiExeption("not found ");
        }
        return l;
    }

}
