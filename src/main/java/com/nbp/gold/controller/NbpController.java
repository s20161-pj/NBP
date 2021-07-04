package com.nbp.gold.controller;

import com.nbp.gold.services.NbpService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/nbp")
public class NbpController {

    private final NbpService nbpService;

    public NbpController(NbpService nbpService){
        this.nbpService = nbpService;
    }

    @GetMapping(value="/goldRates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> calculateGoldRates(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ){
        Double goldRates = nbpService.calculateGoldRates(from, to);

        return ResponseEntity.ok(goldRates);
    }
}