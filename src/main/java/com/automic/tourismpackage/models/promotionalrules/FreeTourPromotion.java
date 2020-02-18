package com.automic.tourismpackage.models.promotionalrules;


import com.automic.tourismpackage.tours.Tour;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FreeTourPromotion implements PromotionalRule {

    private Tour tourPaidFor;
    private Tour tourForFree;
    private String description;
}
