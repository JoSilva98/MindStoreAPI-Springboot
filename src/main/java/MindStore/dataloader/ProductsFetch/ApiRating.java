package MindStore.dataloader.ProductsFetch;

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
