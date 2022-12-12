package at.spengergasse.sj22235bhifpos1scientificpaper.persitance;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
