package at.spengergasse.sj22235bhifpos1scientificpaper.service;

import at.spengergasse.sj22235bhifpos1scientificpaper.persitance.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;


}
