package com.automic.tourismpackage.models.promotionalrules;


import com.automic.tourismpackage.tours.Tour;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DiscountPricePromotion implements PromotionalRule{

    private Tour tour;
    private int minimumTicketsToBeBought;
    private double discountToBeApplied;
    private String description;
}
