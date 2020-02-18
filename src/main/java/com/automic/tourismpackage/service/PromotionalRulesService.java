package com.automic.tourismpackage.service;

import com.automic.tourismpackage.models.Rules;
import com.automic.tourismpackage.models.promotionalrules.PromotionalRule;

import java.util.List;

public interface PromotionalRulesService {

    Rules addPromotionalRule(PromotionalRule rule);

    Rules getPromotionalRuleById(String id);

    List<Rules> getPromotionalRuleByIds(List<String> ids);

    List<Rules> getAllPromotionalRules();

}
