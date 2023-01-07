package at.spengergasse.sj22235bhifpos1scientificpaper.security;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.User;
import at.spengergasse.sj22235bhifpos1scientificpaper.foundation.TemporalValueFactory;
import at.spengergasse.sj22235bhifpos1scientificpaper.persitence.RoleRepository;
import at.spengergasse.sj22235bhifpos1scientificpaper.persitence.UserRepository;
import at.spengergasse.sj22235bhifpos1scientificpaper.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

//tag::CustomOAuth2UserService[]
@RequiredArgsConstructor

@Service("oidcUserService")
public class CustomOAuth2UserService extends OidcUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenService tokenService;
    private final TemporalValueFactory temporalValueFactory;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        try {
            return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());

        Optional<User> userOptional = userRepository.findUserByEmail(oidcUser.getEmail());
        if (!userOptional.isPresent()) {
            User user = User.builder()
                    .googleId(googleUserInfo.getId())
                    .username(googleUserInfo.getName())
                    .email(googleUserInfo.getEmail())
                    .messages(new ArrayList<>())
                    .roles(Arrays.asList(roleRepository.findByName("ROLE_USER")))
                    .creationTS(temporalValueFactory.now())
                    .token(tokenService.createNanoId())
                    .build();

            userRepository.save(user);
        }
        return oidcUser;
    }
}
//end::CustomOAuth2UserService[]