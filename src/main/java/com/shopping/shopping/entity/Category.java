package com.shopping.shopping.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Category {
    private Long id;
    private Integer categoryId;
    private String categoryName;
    private String description;
    private Integer departmentId;
    private Department departmentByDepartmentId;
    private Collection<ProductAndCategory> productAndCategoriesByCategoryId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    @Basic
    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }


    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }


    @Basic
    @Column(name = "department_id")
    public Integer getDepartmentId() {
        return departmentId;
    }


    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    public Department getDepartmentByDepartmentId() {
        return departmentByDepartmentId;
    }


    @OneToMany(mappedBy = "categoryByCategoryId")
    public Collection<ProductAndCategory> getProductAndCategoriesByCategoryId() {
        return productAndCategoriesByCategoryId;
    }
}
