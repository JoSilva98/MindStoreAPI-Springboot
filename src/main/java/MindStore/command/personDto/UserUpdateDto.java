package MindStore.command.personDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UserUpdateDto extends PersonUpdateDto {

    @Size(min = 5, max = 40, message = "Adress should have at least 5 characters")
    private String address;
}
