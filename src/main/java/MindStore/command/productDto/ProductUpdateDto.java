package MindStore.command.productDto;

import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductUpdateDto {
    @Size(min = 2, max = 30, message = "Product name should have at least 2 characters")
    private String title;

    @Min(0)
    @Max(100000)
    private double price;

    @Size(min = 2, max = 500, message = "Description name should have at least 2 characters and 500 max")
    private String description;

    @Size(min = 2, max = 20)
    private String category;

    @Size(min = 2, max = 150, message = "Image should have at least 2 characters and 60 max")
    private String image;

    @Min(0)
    @Max(20)
    private int stock;
}
