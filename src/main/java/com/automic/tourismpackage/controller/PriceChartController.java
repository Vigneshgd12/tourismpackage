package com.automic.tourismpackage.controller;

import com.automic.tourismpackage.models.PriceChart;
import com.automic.tourismpackage.repository.PriceChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/price-chart")
public class PriceChartController {
    @Autowired
    private PriceChartRepository repository;

    @GetMapping
    public List<PriceChart> getAllPriceChart(){
        return repository.findAll();
    }

    @PostMapping
    public PriceChart addNewTour(@RequestBody PriceChart priceChart){
        return repository.save(priceChart);
    }

    @PatchMapping
    public PriceChart updateTourPriceChart(@RequestBody PriceChart priceChart){
        return repository.save(priceChart);
    }

    @GetMapping("/{id}")
    public PriceChart getPriceChartById(@PathVariable String id){
        return repository.findById(id).get();
    }


}
