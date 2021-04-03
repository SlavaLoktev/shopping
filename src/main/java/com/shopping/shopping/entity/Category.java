package com.shopping.shopping.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Data
@Entity
@Table(name = "category", schema = "shoe_shop")
public class Category {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_and_category", schema = "shoe_shop",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productSetByCategory;

    @Override
    public String toString() {
        return categoryName;
    }
}
