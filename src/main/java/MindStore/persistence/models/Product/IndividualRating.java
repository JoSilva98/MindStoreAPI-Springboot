package MindStore.persistence.models.Product;

import MindStore.persistence.models.Person.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "individual_ratings")
public class IndividualRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(nullable = false)
    private int rate;

    @Column(nullable = false)
    private String productTitle;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "average_rating_id_fk")
    private AverageRating averageRatingId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id_fk")
    private User userId;
}
