package MindStore.security;

import MindStore.exceptions.NotFoundException;
import MindStore.persistence.models.Person.Person;
import MindStore.persistence.repositories.Person.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceDetails implements UserDetailsService {
    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = this.personRepository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("Person not found"));
        return PersonAuth.builder()
                .person(person)
                .build();
    }
}
