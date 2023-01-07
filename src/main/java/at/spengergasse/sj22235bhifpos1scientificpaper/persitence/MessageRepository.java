package at.spengergasse.sj22235bhifpos1scientificpaper.persitence;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    Optional<Message> findByToken(String token);
    void deleteByToken(String token);
    boolean existsByToken(String token);
}
