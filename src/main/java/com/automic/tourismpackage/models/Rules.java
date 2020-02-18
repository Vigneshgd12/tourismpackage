package com.automic.tourismpackage.models;

import com.automic.tourismpackage.models.promotionalrules.PromotionalRule;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Rules {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private PromotionalRule rule;
}
