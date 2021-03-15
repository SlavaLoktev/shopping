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
public class Department {
    private Long id;
    private Integer departmentId;
    private String departmentName;
    private String description;
    private Collection<Category> categoriesByDepartmentId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }


    @Id
    @Column(name = "department_id")
    public Integer getDepartmentId() {
        return departmentId;
    }


    @Basic
    @Column(name = "department_name")
    public String getDepartmentName() {
        return departmentName;
    }


    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }



    @OneToMany(mappedBy = "departmentByDepartmentId")
    public Collection<Category> getCategoriesByDepartmentId() {
        return categoriesByDepartmentId;
    }
}
