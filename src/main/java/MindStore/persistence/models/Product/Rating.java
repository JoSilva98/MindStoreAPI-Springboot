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
@Table(name = "ratings")
public class Rating {

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

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "rating_user_list",
            joinColumns = @JoinColumn(name = "rating_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> userList;

    public boolean addUser(User user) {
        return this.userList.add(user);
    }
}
