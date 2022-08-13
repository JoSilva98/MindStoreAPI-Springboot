package MindStore.persistence.models.Person;

import MindStore.persistence.models.Product.IndividualRating;
import MindStore.persistence.models.Product.Product;
import MindStore.persistence.models.Product.AverageRating;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends Person {

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String address;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_product_table",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> shoppingCart = new ArrayList<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.DETACH)
    private List<IndividualRating> individualRatings;

    public void addProductToCart(Product product) {
        this.shoppingCart.add(product);
    }

    public void removeProductFromCart(Product product) {
        this.shoppingCart.remove(product);
    }
}
