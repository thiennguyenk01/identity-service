package hawknet.dev.identity_service.repository;

import hawknet.dev.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
