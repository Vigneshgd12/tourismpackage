package com.automic.tourismpackage.models.promotionalrules;

import com.automic.tourismpackage.tours.Tour;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FreeTicketsPromotion implements PromotionalRule{

    private Tour tour;
    private int ticketsToBeBought;
    private int ticketsToBePaid;
    private String description;
}
