package com.automic.tourismpackage.models.invoice;

import com.automic.tourismpackage.tours.Tour;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TourTicketDetail implements Comparable {

    private Tour tour;
    private int totalTicketsPurchased;
    private double totalPriceToBePaid;
    private double totalActualPrice;
    private String promotionOfferApplied;

    @Override
    public int compareTo(Object o) {
        TourTicketDetail tourTicketDetail = (TourTicketDetail)o;
        if(Double.compare(this.totalPriceToBePaid,tourTicketDetail.getTotalPriceToBePaid()) == 0){
            return 0;
        }else if (Double.compare(this.totalPriceToBePaid,tourTicketDetail.getTotalPriceToBePaid()) < 0){
            return -1;
        }else
            return 1;
    }
}
