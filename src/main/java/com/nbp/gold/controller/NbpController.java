package com.nbp.gold.controller;

import com.nbp.gold.services.NbpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/nbp")
public class NbpController {

    private final NbpService nbpService;

    public NbpController(NbpService nbpService){
        this.nbpService = nbpService;
    }

    @ApiOperation(value = "Get gold rate for selected dates", response = Double.class,
            notes = "Endpoint to get gold rate for selected dates. Response: average of gold rates in selected dates")
    @GetMapping(value="/goldRates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> calculateGoldRates(
            @ApiParam(name ="from", type = "LocalDate", required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @ApiParam(name ="to", type = "LocalDate", required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ){
        Double goldRates = nbpService.calculateGoldRates(from, to);

        return ResponseEntity.ok(goldRates);
    }
}