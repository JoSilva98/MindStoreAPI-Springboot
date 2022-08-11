package MindStore.command;

import MindStore.persistence.models.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CategoryDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Category name should have at least 2 characters")
    private String category;

    //comentado por bug de recursividade
//    @JsonIgnore
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //para nao poderem mexer
//    private List<Product> productList = new ArrayList<>();
}
