package MindStore.dataloader.manual;

import MindStore.persistence.models.Person.Admin;
import MindStore.persistence.models.Person.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class AdminsFactory {
    public static List<Admin> generateAdmins(Role adminRole, PasswordEncoder encoder) {
        Admin admin1 = Admin.builder()
                .firstName("Ze")
                .lastName("To")
                .email("zeto@email.com")
                .password(encoder.encode("ora_esta_bem_entao"))
                .roleId(adminRole)
                .build();

        Admin admin2 = Admin.builder()
                .firstName("João")
                .lastName("Silva")
                .email("john@email.com")
                .password(encoder.encode("agorasouadmin"))
                .roleId(adminRole)
                .build();

        return List.of(admin1, admin2);
    }
}
