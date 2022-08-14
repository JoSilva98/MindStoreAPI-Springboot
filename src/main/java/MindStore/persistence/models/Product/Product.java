package MindStore.persistence.models.Product;

import MindStore.persistence.models.Person.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private double price;

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

    //@OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rating_id_fk")
    private AverageRating ratingId;

    public void decreaseStock() {
        this.stock--;
    }
}
