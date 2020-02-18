package com.automic.tourismpackage.helper;

import com.automic.tourismpackage.models.PriceChart;
import com.automic.tourismpackage.models.ShoppingInvoice;
import com.automic.tourismpackage.models.invoice.TicketDetail;
import com.automic.tourismpackage.models.invoice.TourTicketDetail;
import com.automic.tourismpackage.models.promotionalrules.DiscountPricePromotion;
import com.automic.tourismpackage.models.promotionalrules.FreeTicketsPromotion;
import com.automic.tourismpackage.models.promotionalrules.FreeTourPromotion;
import com.automic.tourismpackage.models.promotionalrules.PromotionalRule;
import com.automic.tourismpackage.tours.Tour;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.automic.tourismpackage.helper.PromotionCalculator.*;
import static com.automic.tourismpackage.helper.PromotionCalculator.calculateFreeTicket;

@Setter
public class ShoppingCart {

    private Map<Tour,Integer> ticketsPerTourMap;

    private List<PriceChart> priceCharts;

    private List<PromotionalRule> promotionalRules;
    private Map<Tour, TourTicketDetail> tourTourTicketDetailMap;

    public ShoppingCart(List<PromotionalRule> promotionalRules){
        this.promotionalRules = promotionalRules;
        this.ticketsPerTourMap = new HashMap<>();
    }

    public void add(Tour tour){
        if(this.ticketsPerTourMap.containsKey(tour)) {
            int count = this.ticketsPerTourMap.get(tour);
            this.ticketsPerTourMap.put(tour, ++count);
        }else{
            this.ticketsPerTourMap.put(tour,1);
        }
    }

    public double total(){
        List<TourTicketDetail> tourTicketDetails = new ArrayList<>();
        tourTicketDetails.addAll(calculateFreeTickets());
        tourTicketDetails.addAll(calculateDiscounts());
        tourTicketDetails.addAll(calculateFreeTours());
        tourTicketDetails.addAll(caculateWithoutRules(ticketsPerTourMap,priceCharts));
        tourTourTicketDetailMap = getTourTourTicketDetailMap(tourTicketDetails);
        return tourTourTicketDetailMap.entrySet().stream().mapToDouble(entry-> entry.getValue().getTotalPriceToBePaid()).sum();
    }


    public ShoppingInvoice getShoppingInvoice(){
        return ShoppingInvoice.builder()
                .total(total())
                .ticketDetails(tourTourTicketDetailMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()))
                .build();
    }


    private List<TourTicketDetail> calculateFreeTickets(){
        List<FreeTicketsPromotion> freeTicketsPromotions = promotionalRules.stream()
                .filter(rules-> rules instanceof FreeTicketsPromotion)
                .map(rule -> (FreeTicketsPromotion)rule).collect(Collectors.toList());
        return calculateFreeTicket(freeTicketsPromotions,ticketsPerTourMap,priceCharts);
    }


    private List<TourTicketDetail> calculateFreeTours(){
        List<FreeTourPromotion> freeTourPromotions = promotionalRules.stream()
                .filter(rules-> rules instanceof FreeTourPromotion)
                .map(rule -> (FreeTourPromotion)rule).collect(Collectors.toList());
        return calculateFreeTour(freeTourPromotions,ticketsPerTourMap,priceCharts);
    }


    private List<TourTicketDetail> calculateDiscounts(){
        List<DiscountPricePromotion> discountPricePromotions = promotionalRules.stream()
                .filter(rules-> rules instanceof DiscountPricePromotion)
                .map(rule -> (DiscountPricePromotion)rule).collect(Collectors.toList());
        return calculateDiscount(discountPricePromotions,ticketsPerTourMap,priceCharts);
    }



    private Map<Tour, TourTicketDetail> getTourTourTicketDetailMap(List<TourTicketDetail> tourTicketDetails) {
        Map<Tour,TourTicketDetail> map = new HashMap<>();
        tourTicketDetails.stream().forEach(tourTicketDetail -> {
            if(map.containsKey(tourTicketDetail.getTour())){
                TourTicketDetail minimumPrice = map.get(tourTicketDetail.getTour());
                if(minimumPrice.getTotalPriceToBePaid()>tourTicketDetail.getTotalPriceToBePaid()){
                    map.put(tourTicketDetail.getTour(),tourTicketDetail);
                }
            }else{
                map.put(tourTicketDetail.getTour(),tourTicketDetail);
            }
        });
        return map;
    }
}
