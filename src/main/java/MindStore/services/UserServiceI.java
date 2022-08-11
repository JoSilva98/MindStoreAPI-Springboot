package MindStore.services;

import MindStore.command.ProductDto;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface UserServiceI {
    Set<ProductDto> getAllProducts();
}
