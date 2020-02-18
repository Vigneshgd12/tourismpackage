package com.automic.tourismpackage.repository;

import com.automic.tourismpackage.models.PriceChart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceChartRepository extends MongoRepository<PriceChart, String> {
}
