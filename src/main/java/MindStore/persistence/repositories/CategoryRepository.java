package MindStore.persistence.repositories;

import MindStore.persistence.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    //query para ir buscar a tabela categories od é igual ao parametro da funçao de baixo
    @Query(value = "SELECT * From categories WHERE categories.category = :category", nativeQuery = true)
    Optional<Category> findByCategory(String category);

}
