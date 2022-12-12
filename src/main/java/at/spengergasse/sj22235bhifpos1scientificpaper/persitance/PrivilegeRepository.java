package at.spengergasse.sj22235bhifpos1scientificpaper.persitance;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);
}
