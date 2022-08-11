package MindStore.services;

import MindStore.persistence.repositories.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    private AdminRepository adminRepository;
}
