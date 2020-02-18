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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PromotionCalculator {

    public static List<TourTicketDetail> calculateFreeTicket(List<FreeTicketsPromotion> rules, Map<Tour,Integer> ticketsPerTourMap, List<PriceChart> priceCharts){
        List<TourTicketDetail> tourTicketDetails = new ArrayList<>();
        rules.stream().forEach(freeTicketsPromotion -> {
            Tour tour = freeTicketsPromotion.getTour();
            if(ticketsPerTourMap.containsKey(tour)) {
                int ticketsToBeBought = freeTicketsPromotion.getTicketsToBeBought();
                int ticketsToBePaid = freeTicketsPromotion.getTicketsToBePaid();
                double priceOfTour = getTourPrice(priceCharts, tour);
                int totalTickets = ticketsPerTourMap.get(tour);

                double promotionsAppliedPrice = (totalTickets/ticketsToBeBought) * priceOfTour * ticketsToBePaid;
                double actualPrice = (totalTickets%ticketsToBeBought) * priceOfTour;
                tourTicketDetails.add(TourTicketDetail.builder()
                        .tour(tour)
                        .totalPriceToBePaid(promotionsAppliedPrice+actualPrice)
                        .totalActualPrice(totalTickets*priceOfTour)
                        .totalTicketsPurchased(totalTickets)
                        .promotionOfferApplied(freeTicketsPromotion.getDescription())
                        .build());
            }
        });

        return tourTicketDetails;
    }


    public static List<TourTicketDetail> calculateFreeTour(List<FreeTourPromotion> rules, Map<Tour,Integer> ticketsPerTourMap, List<PriceChart> priceCharts){
        List<TourTicketDetail> tourTicketDetails = new ArrayList<>();
        rules.stream().forEach(freeTourPromotion -> {
            Tour paidTour = freeTourPromotion.getTourPaidFor();
            Tour freeTour = freeTourPromotion.getTourForFree();
            if(ticketsPerTourMap.containsKey(paidTour) && ticketsPerTourMap.containsKey(freeTour)) {
                double priceOfPaidTour = getTourPrice(priceCharts, paidTour);
                double priceOfFreeTour = getTourPrice(priceCharts, freeTour);
                int paidTourTickets = ticketsPerTourMap.get(paidTour);
                int freeTourTickets = ticketsPerTourMap.get(freeTour);
                tourTicketDetails.add(TourTicketDetail.builder()
                        .tour(paidTour)
                        .totalPriceToBePaid(paidTourTickets*priceOfPaidTour)
                        .totalTicketsPurchased(paidTourTickets)
                        .totalActualPrice(paidTourTickets*priceOfPaidTour)
                        .build());
                tourTicketDetails.add(TourTicketDetail.builder()
                        .tour(freeTour)
                        .totalPriceToBePaid(paidTourTickets>freeTourTickets?0:(freeTourTickets-paidTourTickets)*priceOfFreeTour)
                        .totalTicketsPurchased(freeTourTickets)
                        .totalActualPrice(freeTourTickets*priceOfFreeTour)
                        .promotionOfferApplied(freeTourPromotion.getDescription())
                        .build());
            }
        });

        return tourTicketDetails;
    }



    public static List<TourTicketDetail> calculateDiscount(List<DiscountPricePromotion> rules, Map<Tour,Integer> ticketsPerTourMap, List<PriceChart> priceCharts){
        List<TourTicketDetail> tourTicketDetails = new ArrayList<>();
        rules.stream().forEach(discountPricePromotion -> {
            Tour tour = discountPricePromotion.getTour();
            if(ticketsPerTourMap.containsKey(tour)) {
                int minimumTicketsToBeBought = discountPricePromotion.getMinimumTicketsToBeBought();
                double discountToBeApplied = discountPricePromotion.getDiscountToBeApplied();
                double priceOfTour = getTourPrice(priceCharts, tour);
                int totalTickets = ticketsPerTourMap.get(tour);

                tourTicketDetails.add(TourTicketDetail.builder()
                        .tour(tour)
                        .totalPriceToBePaid(totalTickets>minimumTicketsToBeBought
                                ?totalTickets*(priceOfTour-discountToBeApplied)
                                :totalTickets*priceOfTour)
                        .totalTicketsPurchased(totalTickets)
                        .totalActualPrice(totalTickets*priceOfTour)
                        .promotionOfferApplied(discountPricePromotion.getDescription())
                        .build());
            }
        });

        return tourTicketDetails;
    }



    public static List<TourTicketDetail> caculateWithoutRules(Map<Tour,Integer> ticketsPerTourMap, List<PriceChart> priceCharts){
        List<TourTicketDetail> tourTicketDetails = new ArrayList<>();

        ticketsPerTourMap.entrySet().stream().forEach(tourIntegerEntry -> {
            Tour tour = tourIntegerEntry.getKey();
            int tickets = tourIntegerEntry.getValue();
            double price = getTourPrice(priceCharts, tour);
            tourTicketDetails.add(TourTicketDetail.builder().tour(tour)
                    .totalActualPrice(tickets*price)
                    .promotionOfferApplied("No offer applied")
                    .totalPriceToBePaid(tickets*price).totalTicketsPurchased(tickets).build());
        });
        return tourTicketDetails;
    }


    private static double getTourPrice(List<PriceChart> priceCharts, Tour tour) {
        return priceCharts.stream()
                .filter(priceChart -> priceChart.getCode().equals(tour))
                .mapToDouble(priceChart -> priceChart.getPrice()).findFirst().getAsDouble();
    }
}
