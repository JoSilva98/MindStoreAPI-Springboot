package MindStore.command.productDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class IndividualRatingDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int rate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String productTitle;
}
