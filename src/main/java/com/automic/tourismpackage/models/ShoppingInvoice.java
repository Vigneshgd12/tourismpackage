package com.automic.tourismpackage.models;


import com.automic.tourismpackage.models.invoice.TourTicketDetail;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Builder
@Setter
public class ShoppingInvoice {
    private List<TourTicketDetail> ticketDetails;
    private double total;
}
