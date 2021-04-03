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


@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Data
@Entity
@Table(name = "department", schema = "shoe_shop")
public class Department {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    List<Category> categories;

    @Override
    public String toString() {
        return departmentName;
    }
}
