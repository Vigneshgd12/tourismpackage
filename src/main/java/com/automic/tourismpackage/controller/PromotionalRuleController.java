package com.automic.tourismpackage.controller;

import com.automic.tourismpackage.models.Rules;
import com.automic.tourismpackage.models.promotionalrules.DiscountPricePromotion;
import com.automic.tourismpackage.models.promotionalrules.FreeTourPromotion;
import com.automic.tourismpackage.models.promotionalrules.FreeTicketsPromotion;
import com.automic.tourismpackage.service.PromotionalRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promotion-rule")
public class PromotionalRuleController {

    @Autowired
    private PromotionalRulesService service;


    @PostMapping("/discount")
    public Rules addDiscount(@RequestBody DiscountPricePromotion discount){
        return service.addPromotionalRule(discount);
    }

    @PostMapping("/free-tour")
    public Rules addFreeTour(@RequestBody FreeTourPromotion freeTour){
        return service.addPromotionalRule(freeTour);
    }

    @PostMapping("/free-ticket")
    public Rules addFreeTicket(@RequestBody FreeTicketsPromotion freeTicket){
       return service.addPromotionalRule(freeTicket);
    }

    @GetMapping
    public List<Rules> getAllPromotionalRules(){
        return service.getAllPromotionalRules();
    }

    @GetMapping("/{id}")
    public Rules getPromotionalRuleById(@PathVariable String id){
        return service.getPromotionalRuleById(id);
    }

}
