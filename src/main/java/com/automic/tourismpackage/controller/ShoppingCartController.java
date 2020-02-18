package com.automic.tourismpackage.controller;

import com.automic.tourismpackage.delegates.ShoppingCartDelegate;
import com.automic.tourismpackage.models.ShoppingInvoice;
import com.automic.tourismpackage.models.invoice.PurchaseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/purchase-tickets")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartDelegate delegate;

    @PostMapping
    public ShoppingInvoice getTickets(@RequestBody PurchaseList purchaseList){
        return delegate.purchaseTickets(purchaseList);
    }
}
