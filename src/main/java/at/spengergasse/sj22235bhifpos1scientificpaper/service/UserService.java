package at.spengergasse.sj22235bhifpos1scientificpaper.service;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.User;
import at.spengergasse.sj22235bhifpos1scientificpaper.foundation.AuthenticationFacade;
import at.spengergasse.sj22235bhifpos1scientificpaper.foundation.TemporalValueFactory;
import at.spengergasse.sj22235bhifpos1scientificpaper.persitance.RoleRepository;
import at.spengergasse.sj22235bhifpos1scientificpaper.persitance.UserRepository;
import at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www.forms.CreateUserForm;
import at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www.error.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
@Transactional(readOnly = true)
public class UserService{

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final TemporalValueFactory temporalValueFactory;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public String getAuthenticatedUsername(){
        Optional<User> user = userRepository.findUserByEmail(authenticationFacade.getAuthentication().getName())
                .or(() -> userRepository.findUserByGoogleId(authenticationFacade.getAuthentication().getName()));
        return  (user.isPresent()) ?
                user.get().getUsername() :
                "nobody";
    }

    @Transactional(readOnly = false)
    public User registerNewUserAccount(CreateUserForm form) {
        if (userRepository.existsByEmail(form.getEmail()))
            throw new UserAlreadyExistException(String.format("There is an account with the email address %s", form.getEmail()));

        return userRepository.save(User.builder()
                        .username(form.getUsername())
                        .email(form.getEmail())
                        .password(passwordEncoder.encode(form.getPassword()))
                        .messages(new ArrayList<>())
                        .token(tokenService.createNanoId())
                        .creationTS(temporalValueFactory.now())
                        .roles(Arrays.asList(roleRepository.findByName("ROLE_USER")))
                .build());
    }
}
