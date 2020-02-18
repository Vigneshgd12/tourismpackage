package com.automic.tourismpackage.service.impl;

import com.automic.tourismpackage.models.Rules;
import com.automic.tourismpackage.models.promotionalrules.PromotionalRule;
import com.automic.tourismpackage.repository.PromotionalRulesRepository;
import com.automic.tourismpackage.service.PromotionalRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionalRulesServiceImpl implements PromotionalRulesService {

    @Autowired
    PromotionalRulesRepository repository;

    @Override
    public Rules addPromotionalRule(PromotionalRule rule) {
        return repository.save(Rules.builder().rule(rule).build());
    }


    @Override
    public Rules getPromotionalRuleById(String id) {
        return repository.findById(id).get();
    }


    @Override
    public List<Rules> getPromotionalRuleByIds(List<String> ids) {
        return (List<Rules>) repository.findAllById(ids);
    }


    @Override
    public List<Rules> getAllPromotionalRules() {
        return repository.findAll();
    }
}
