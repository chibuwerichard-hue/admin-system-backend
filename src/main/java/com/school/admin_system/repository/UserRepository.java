package com.school.admin_system.repository;
import com.school.admin_system.entity.User;
import com.school.admin_system.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(UserRole role);
    Boolean existsByEmail(String email);
}
