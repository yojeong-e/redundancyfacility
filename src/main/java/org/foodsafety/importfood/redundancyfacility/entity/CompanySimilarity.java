package org.foodsafety.importfood.redundancyfacility.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;


@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class CompanySimilarity {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int id;
    private Company company;
    private Double companySimilarity;


}
