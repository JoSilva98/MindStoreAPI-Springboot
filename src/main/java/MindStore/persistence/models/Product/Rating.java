package MindStore.persistence.models.Product;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private int id;

    @Column(nullable = false)
    private double rate;

    @Column(nullable = false)
    private int count;

    @OneToOne(mappedBy = "ratingId")
    private Product productId;
}
