package com.automic.tourismpackage.repository;

import com.automic.tourismpackage.models.Rules;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionalRulesRepository extends MongoRepository<Rules,String> {
}
