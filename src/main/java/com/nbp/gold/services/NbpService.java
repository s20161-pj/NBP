package com.nbp.gold.services;

import com.nbp.gold.model.GoldRate;
import com.nbp.gold.model.GoldRateNbpModel;
import com.nbp.gold.model.MetalType;
import com.nbp.gold.repository.NbpRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NbpService{
    private final RestTemplate restTemplate;
    private final NbpRepository nbpRepository;

    public NbpService(RestTemplate restTemplate, NbpRepository nbpRepository){
        this.restTemplate = restTemplate;
        this.nbpRepository = nbpRepository;
    }

    public Double calculateGoldRates(LocalDate from, LocalDate to){
        // NBP {date}, {startDate}, {endDate} – data w formacie RRRR-MM-DD (standard ISO 8601)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fromDate = from.format(formatter);
        String toDate = to.format(formatter);

        String apiUrl = "http://api.nbp.pl/api/cenyzlota/"+fromDate+"/"+toDate;
        GoldRateNbpModel[] goldRates = restTemplate.getForObject(apiUrl, GoldRateNbpModel[].class);
        double average = calculate(goldRates);

        GoldRate goldRate = new GoldRate(null, MetalType.Gold, from, to, LocalDateTime.now(), average);
        this.nbpRepository.save(goldRate);

        return average;
    }

    public double calculate(GoldRateNbpModel[] goldRates){
        double priceSum =0;
        long count= goldRates.length;
        for(GoldRateNbpModel rate : goldRates){
            double price = rate.getCena();
            priceSum = priceSum + price;
        }
        return  count > 0 ? priceSum / count : 0.0d;
    }

}
