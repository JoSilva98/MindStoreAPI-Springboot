package MindStore.persistence.repositories.Product;

import MindStore.persistence.models.Product.AverageRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AverageRatingRepository extends JpaRepository<AverageRating, Long> {
    @Query(value = "SELECT * FROM average_ratings " +
            "INNER JOIN products " +
            "ON average_ratings.id = products.rating_id_fk " +
            "WHERE products.title = :productTitle", nativeQuery = true)
    Optional<AverageRating> findByProductTitle(String productTitle);
}
