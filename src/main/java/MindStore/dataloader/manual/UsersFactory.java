package MindStore.dataloader.manual;

import MindStore.persistence.models.Person.Role;
import MindStore.persistence.models.Person.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

public class UsersFactory {
    public static List<User> generateUsers(Role userRole, PasswordEncoder encoder) {
        User user1 = User.builder()
                .firstName("Joaquim")
                .lastName("Alberto")
                .email("quim@email.com")
                .password(encoder.encode("password"))
                .dateOfBirth(LocalDate.of(1973, 2, 23))
                .address("Rua do Quim, 3800-237")
                .roleId(userRole)
                .build();

        User user2 = User.builder()
                .firstName("Ana")
                .lastName("Costa")
                .email("ana@email.com")
                .password(encoder.encode("qwe123ytr654"))
                .dateOfBirth(LocalDate.of(1996, 6, 16))
                .address("Rua da Ana, 3801-238")
                .roleId(userRole)
                .build();

        User user3 = User.builder()
                .firstName("João")
                .lastName("Couto")
                .email("coutinho@email.com")
                .password(encoder.encode("incriiiiiivel"))
                .dateOfBirth(LocalDate.of(1997, 9, 13))
                .address("Rua do Coutinho, 3802-239")
                .roleId(userRole)
                .build();

        User user4 = User.builder()
                .firstName("Luís")
                .lastName("Couto")
                .email("luisinho@email.com")
                .password(encoder.encode("asvezespareceque"))
                .dateOfBirth(LocalDate.of(1999, 10, 22))
                .address("Rua do Coutinho, 3802-239")
                .roleId(userRole)
                .build();

        User user5 = User.builder()
                .firstName("Elisa")
                .lastName("Moutinho")
                .email("elisinha@email.com")
                .password(encoder.encode("palavrapassdificil"))
                .dateOfBirth(LocalDate.of(1989, 12, 22))
                .address("Rua da Elisa, 3803-240")
                .roleId(userRole)
                .build();

        User user6 = User.builder()
                .firstName("Ala")
                .lastName("Kropa")
                .email("alinha@email.com")
                .password(encoder.encode("sqlinjection"))
                .dateOfBirth(LocalDate.of(1993, 10, 7))
                .address("Rua da Ala, 3804-241")
                .roleId(userRole)
                .build();

        User user7 = User.builder()
                .firstName("Nuno")
                .lastName("Carmo")
                .email("nuninho@email.com")
                .password(encoder.encode("mesmoaniversariodojoao"))
                .dateOfBirth(LocalDate.of(1995, 9, 13))
                .address("Rua do Nuno, 3805-242")
                .roleId(userRole)
                .build();

        User user8 = User.builder()
                .firstName("Carolina")
                .lastName("Ferraz")
                .email("carolininha@email.com")
                .password(encoder.encode("pequenina"))
                .dateOfBirth(LocalDate.of(2004, 8, 26))
                .address("Rua da Carolina, 3806-243")
                .roleId(userRole)
                .build();

        return List.of(user1, user2, user3, user4, user5, user6, user7, user8);
    }
}
