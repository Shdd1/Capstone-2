package com.example.livestockmanagment.Service;

import com.example.livestockmanagment.Api.ApiExeption;
import com.example.livestockmanagment.Model.Farm;
import com.example.livestockmanagment.Model.Livestock;
import com.example.livestockmanagment.Model.Seller;
import com.example.livestockmanagment.Repository.FarmRepository;
import com.example.livestockmanagment.Repository.LivestockRepository;
import jakarta.persistence.GeneratedValue;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LivestockService {
    private final LivestockRepository livestockRepository;
    private final FarmRepository farmRepository;
    public List<Livestock> getLivestock() {
        return livestockRepository.findAll();
    }
    public void addLivestock(Livestock livestock){
        Farm f=farmRepository.findFarmById(livestock.getFarmId());
        if(f==null || f.getStock()==f.getCapacity()){
            throw new ApiExeption("farm not found or the farm is full");
        }
       f.setStock(f.getStock()+1);
        f.setStatus("available");
        livestock.setStatus("Available");
        livestockRepository.save(livestock);
    }
    public void updateLivestock(Integer id,Livestock livestock){
        Livestock l=livestockRepository.findLivestockById(id);
        if(l==null){
            throw new ApiExeption("not found");
        }
       l.setWeight(livestock.getWeight());
        l.setStatus(livestock.getStatus());
        l.setPrice(livestock.getPrice());
        l.setLivestockType(livestock.getLivestockType());
        l.setName(livestock.getName());
        //l.setHealthCertificate(livestock.getHealthCertificate());
        l.setGender(livestock.getGender());
        l.setAge(livestock.getAge());
        livestockRepository.save(l);
    }

    public void deleteLivestock(Integer id){
        Livestock l=livestockRepository.findLivestockById(id);
        if(l==null){
            throw new ApiExeption("user not found");
        }
        livestockRepository.delete(l);
    }

    //1: Calculate The Price if livestock type is camels
    public void CalculateThePriceOfCamels(Integer id){
           Livestock l=livestockRepository.findLivestockById(id);
        if(l.getLivestockType().equalsIgnoreCase("Horses")){
            throw new ApiExeption("is Horses different price");
        }
           //if age between 2-1 year
             if(l.getLivestockType().equalsIgnoreCase("Camels")&&l.getAge()<=2 && l.getGender().equalsIgnoreCase("f") && l.getWeight()<=200){
                 l.setPrice(26000);
             } else if (l.getLivestockType().equalsIgnoreCase("Camels")&&l.getAge()<=2 && l.getGender().equalsIgnoreCase("m") && l.getWeight()<=200) {
                 l.setPrice(18000);
           //if age between 3-6 Year
             } else if (l.getLivestockType().equalsIgnoreCase("Camels")&&l.getAge() > 2 && l.getAge()<=6 && l.getGender().equalsIgnoreCase("f") && l.getWeight()>=300) {
                 l.setPrice(54000);
             } else if (l.getLivestockType().equalsIgnoreCase("Camels")&&l.getAge() > 2 && l.getAge()<=6 && l.getGender().equalsIgnoreCase("m") && l.getWeight()>=300) {
                 l.setPrice(37000);
                 //if age >6 Year
             } else if (l.getLivestockType().equalsIgnoreCase("Camels")&&l.getAge() > 6 && l.getGender().equalsIgnoreCase("f") && l.getWeight()>400) {
                 l.setPrice(15000);
             } else if (l.getLivestockType().equalsIgnoreCase("Camels")&&l.getAge() > 6 && l.getGender().equalsIgnoreCase("m") && l.getWeight()>400) {
                 l.setPrice(11000);
             }
            livestockRepository.save(l);
    }

    //2: Calculate The Price if livestock type is Horses
    public void CalculateThePriceOfHorses(Integer id) {
        Livestock l = livestockRepository.findLivestockById(id);
        if(l.getLivestockType().equalsIgnoreCase("Camels")){
            throw new ApiExeption("is camels different price");
        }

        //if age between 2-1 year
        if (l.getLivestockType().equalsIgnoreCase("Horses") && l.getAge() <= 2 && l.getGender().equalsIgnoreCase("f") && l.getWeight() <= 300) {
            l.setPrice(30000);
        } else if (l.getLivestockType().equalsIgnoreCase("Horses") && l.getAge() <= 2 && l.getGender().equalsIgnoreCase("m") && l.getWeight() <= 300) {
            l.setPrice(26000);
            //if age between 3-6 Year
        } else if (l.getLivestockType().equalsIgnoreCase("Horses") && l.getAge() > 2 && l.getAge()<=7 && l.getGender().equalsIgnoreCase("f") && l.getWeight() > 300) {
            l.setPrice(56000);
        } else if (l.getLivestockType().equalsIgnoreCase("Horses") && l.getAge() > 2 && l.getAge() <=7 && l.getGender().equalsIgnoreCase("m") && l.getWeight() > 300) {
            l.setPrice(40000);
            //if age >6 Year
        } else if (l.getLivestockType().equalsIgnoreCase("Horses") && l.getAge() >7 && l.getGender().equalsIgnoreCase("f") && l.getWeight() > 450) {
            l.setPrice(16000);
        } else if (l.getLivestockType().equalsIgnoreCase("Horses") && l.getAge() > 7 && l.getGender().equalsIgnoreCase("m") && l.getWeight() > 450) {
            l.setPrice(12000);
        }

        livestockRepository.save(l);
    }
    //7:get livestock in the farm by Status
    public List<Livestock> getLiveAvailable(Integer farmId,String status){
        List<Livestock> l=livestockRepository.findLivestockByFarmIdAndStatus(farmId,status);
        if(l.isEmpty()){
            throw new ApiExeption("not found");
        }
     return l;
    }

    //9:get livestock between two age
    public List<Livestock> livestockBetweenTwoAge(String type,int minAge,int maxAge){
        List<Livestock> l=livestockRepository.findLivestockByLivestockTypeAndAge(type,minAge,maxAge);
        if(l.isEmpty()){
            throw new ApiExeption("not found");
        }
        return l;
    }
//10: order by price
    public List<Livestock> orderByPrice(String type){
        List<Livestock> l=livestockRepository.findLivestockByOrderPrice(type);
        if(l.isEmpty()){
            throw new ApiExeption("not found");
        }
        return l;
    }




}
