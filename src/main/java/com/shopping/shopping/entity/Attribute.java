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
@Table(name = "attribute", schema = "shoe_shop")
public class Attribute {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "attribute_id")
    private Long attributeId;

    @Column(name = "attribute_name")
    private String attributeName;

    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY)
    List<AttrValue> attrValues;

    @Override
    public String toString() {
        return attributeName;
    }
}
