package MindStore.config;

import MindStore.exceptions.ConflictException;
import MindStore.persistence.repositories.Person.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckAuth {
    private final PersonRepository personRepository;

    public void checkUserId(Long id) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .toString();

        this.personRepository.findByEmail(email)
                .ifPresent(x -> {
                    if (!x.getId().equals(id))
                        throw new ConflictException("This id is not yours");
                });
    }
}
