package MindStore.persistence.models;

import MindStore.dataloader.ProductsFetch.ApiRating;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

//props iguais as da external api para podermos mapear

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double price;

    //se nao pusermos isto a description n funciona porque o default é menor
    @Column(nullable = false, length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id_fk")
    private Category category;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int stock;

    @ManyToMany(mappedBy = "shoppingCart")
    private Set<User> users;

    //@OnDelete() se o detach n funcionar
    @OneToOne(cascade = CascadeType.DETACH) //DETACH????
    @JoinColumn(name = "rating_id_fk") //do lado que pusermos o join colum é a table od vai aparecer
    private Rating ratingId;
}
