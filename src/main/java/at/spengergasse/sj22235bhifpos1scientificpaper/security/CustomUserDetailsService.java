package at.spengergasse.sj22235bhifpos1scientificpaper.security;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.Privilege;
import at.spengergasse.sj22235bhifpos1scientificpaper.domain.Role;
import at.spengergasse.sj22235bhifpos1scientificpaper.domain.User;
import at.spengergasse.sj22235bhifpos1scientificpaper.persitance.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with email: %s", email)));
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getRoles()));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles){
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private static List<String> getPrivileges(Collection<Role> roles) {
        final List<String> privileges = new ArrayList<>();
        final List<Privilege> collection = new ArrayList<>();

        roles.stream().map((role) -> {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
            return role;
        });

        collection.stream().map(privilege -> privileges.add(privilege.getName()));

        return privileges;
    }

    private static Collection<? extends GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        privileges.stream().map(privilege -> authorities.add(new SimpleGrantedAuthority(privilege)));
        return authorities;
    }
}
