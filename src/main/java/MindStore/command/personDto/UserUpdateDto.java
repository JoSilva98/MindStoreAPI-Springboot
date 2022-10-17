package MindStore.command.personDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UserUpdateDto extends PersonUpdateDto {

    @Size(min = 5, max = 40, message = "Address should have at least 5 characters")
    private String address;

    @Size(max = 500, message = "Image url too big")
    private String image;
}
