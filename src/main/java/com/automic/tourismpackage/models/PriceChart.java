package com.automic.tourismpackage.models;

import com.automic.tourismpackage.tours.Tour;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@UniqueConstraint(columnNames = {"name","id"})
public class PriceChart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @NotNull
    private Tour code;
    @NotNull
    private String name;
    @NotNull
    private double price;
}
