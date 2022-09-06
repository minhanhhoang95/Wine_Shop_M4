package com.cg.model;


import com.cg.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String slug;

    private String image;

    private Long sold = 0L;



    @Column(precision = 12, scale = 0)
    private BigDecimal price;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

    public ProductDTO toProductDTO() {
        return new ProductDTO ()
                .setId ( id )
                .setTitle ( title )
                .setSlug ( slug )
                .setImage ( image )
                .setSold ( sold )
                .setPrice ( price )
                .setCategory ( category.toCategoryDTO () );
    }
}
