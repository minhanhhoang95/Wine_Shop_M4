package com.cg.model;

import com.cg.model.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
@Accessors(chain = true)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String slug;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public CategoryDTO toCategoryDTO() {
        return new CategoryDTO()
                .setId(id)
                .setTitle(title);
    }
}
