package com.rozhdev.ordee.product.entity;

import com.rozhdev.ordee.commons.db.BaseEntity;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
public class Product extends BaseEntity {
    private String name;
    private BigDecimal price;
    private String description;
}

