package at.wirecube.examples.products.application.entity;

import at.wirecube.examples.products.application.enums.Vat;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Vat vat;
}
