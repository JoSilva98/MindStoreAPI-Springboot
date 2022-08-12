package MindStore.command;

import MindStore.persistence.models.Product.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RatingDto {


        @Id
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private int id;

        @Min(0)
        @Max(5)
        private double rate;

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private int count;

}
