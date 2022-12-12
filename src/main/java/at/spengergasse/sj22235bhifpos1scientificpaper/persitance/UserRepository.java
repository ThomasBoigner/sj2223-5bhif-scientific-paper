package at.spengergasse.sj22235bhifpos1scientificpaper.persitance;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByToken(String token);
    void deleteByToken(String token);
    boolean existsByToken(String token);
    boolean existsByEmail(String email);
    Optional<User> findUserByEmail(String email);
}
