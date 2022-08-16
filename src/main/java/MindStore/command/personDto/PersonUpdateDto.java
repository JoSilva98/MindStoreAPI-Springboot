package MindStore.command.personDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PersonUpdateDto {

    @Size(min = 2, max = 30, message = "Name should have at least 2 characters")
    private String firstName;

    @Size(min = 2, max = 30, message = "Name should have at least 2 characters")
    private String lastName;

    @Email
    @Size(min = 4, max = 30, message = "The email must be between 8 and 30 characters")
    private String email;

    @Size(min = 8, max = 16, message = "The password must be between 8 and 30 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String image;
}
