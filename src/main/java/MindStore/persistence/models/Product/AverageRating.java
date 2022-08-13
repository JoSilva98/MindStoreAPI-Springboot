package MindStore.persistence.models.Product;

import MindStore.persistence.models.Person.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "average_ratings")
public class AverageRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(nullable = false)
    private double rate;

    @Column(nullable = false)
    private int count;

    @OneToOne(mappedBy = "ratingId")
    private Product productId;

    @OneToMany(mappedBy = "averageRatingId", cascade = CascadeType.DETACH)
    private List<IndividualRating> individualRatings;

    public void setAverageRate() {
        this.rate = this.individualRatings
                .stream()
                .mapToDouble(IndividualRating::getRate)
                .average().orElse(0);
    }
}
