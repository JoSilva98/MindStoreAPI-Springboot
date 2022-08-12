package MindStore.persistence.models.Product;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String category;

    @OneToMany(mappedBy = "category")
    private List<Product> productList = new ArrayList<>();
}
