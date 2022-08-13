package MindStore.persistence.repositories.Product;

import MindStore.persistence.models.Product.AverageRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AverageRatingRepository extends JpaRepository<AverageRating, Long> {
}
