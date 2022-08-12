package MindStore.persistence.repositories.Product;

import MindStore.persistence.models.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByTitle(String title);

    @Query(value = "SELECT * From products WHERE products.title LIKE %?1%", nativeQuery = true) //%?1% é o 1º param da funçao
    List<Product> findByTitleLike(String title);

}
