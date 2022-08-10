package MindStore.persistence.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends Person{

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String adress;

    @OneToMany(mappedBy = "userId")
    private List<Product> shoppingCart;
}
