package MindStore.persistence.repositories.Product;

import MindStore.persistence.models.Product.IndividualRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualRatingRepository extends JpaRepository<IndividualRating, Long> {

}
