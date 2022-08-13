package MindStore.persistence.models.Person;

import MindStore.persistence.models.Product.Product;
import MindStore.persistence.models.Product.Rating;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
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

    @ManyToMany(mappedBy = "userList")
    private Set<Rating> ratingList;

    public void addProductToCart(Product product) {
        this.shoppingCart.add(product);
    }

    public void removeProductFromCart(Product product) {
        this.shoppingCart.remove(product);
    }

    public boolean addRating(Rating rating) {
        return this.ratingList.add(rating);
    }
}
