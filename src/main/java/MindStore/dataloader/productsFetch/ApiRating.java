package MindStore.dataloader.productsFetch;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ApiRating {
    private double rate;
    private int count;
}
