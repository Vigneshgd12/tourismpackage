package com.automic.tourismpackage.models.invoice;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PurchaseList {
    private List<TicketDetail> ticketDetails;
    private List<String> promotionsApplied;
}
