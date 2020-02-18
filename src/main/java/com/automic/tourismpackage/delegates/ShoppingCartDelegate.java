package com.automic.tourismpackage.delegates;

import com.automic.tourismpackage.helper.ShoppingCart;
import com.automic.tourismpackage.models.Rules;
import com.automic.tourismpackage.models.ShoppingInvoice;
import com.automic.tourismpackage.models.invoice.PurchaseList;
import com.automic.tourismpackage.models.promotionalrules.DiscountPricePromotion;
import com.automic.tourismpackage.models.promotionalrules.FreeTicketsPromotion;
import com.automic.tourismpackage.models.promotionalrules.FreeTourPromotion;
import com.automic.tourismpackage.models.promotionalrules.PromotionalRule;
import com.automic.tourismpackage.repository.PriceChartRepository;
import com.automic.tourismpackage.service.PromotionalRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingCartDelegate {

    @Autowired
    private PromotionalRulesService promotionalRulesService;

    @Autowired
    private PriceChartRepository priceChartRepository;


    public ShoppingInvoice purchaseTickets(PurchaseList purchaseList) {
        List<Rules> rulesApplied = promotionalRulesService.getPromotionalRuleByIds(purchaseList.getPromotionsApplied());
        List<PromotionalRule> promotionalRules = rulesApplied.stream().map(rules -> rules.getRule()).collect(Collectors.toList());
        ShoppingCart shoppingCart = new ShoppingCart(promotionalRules);
        purchaseList.getTicketDetails().stream().forEach(ticketDetail -> {
            int tickets = ticketDetail.getTicketsBought();
            while(tickets>0){
                shoppingCart.add(ticketDetail.getTour());
                tickets--;
            }
        });
        shoppingCart.setPriceCharts(priceChartRepository.findAll());
        return shoppingCart.getShoppingInvoice();
    }
}
