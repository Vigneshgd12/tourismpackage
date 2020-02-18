package com.automic.tourismpackage.models.invoice;

import com.automic.tourismpackage.tours.Tour;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TicketDetail {
    private Tour tour;
    private int ticketsBought;
}
