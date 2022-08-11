package MindStore.persistence.repositories;

import MindStore.command.ProductDto;
import MindStore.persistence.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * From products WHERE products.title LIKE %?1%", nativeQuery = true) //%?1% é o 1º param da funçao
    List<Product> findByTitle(String title);

}
