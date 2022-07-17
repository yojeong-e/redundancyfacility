package org.foodsafety.importfood.redundancyfacility.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "overseacompany")
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper=false)
@Data
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 150)
    private String companyName;

    @Column(nullable = false)
    private String companyAddress;

    @Column(nullable = false)
    private String companyCountry;

    @Column(nullable = false)
    private String companyCode;

}
