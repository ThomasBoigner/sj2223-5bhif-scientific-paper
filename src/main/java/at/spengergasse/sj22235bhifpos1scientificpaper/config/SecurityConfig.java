package at.spengergasse.sj22235bhifpos1scientificpaper.config;

import com.sun.jdi.connect.Connector;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;


@RequiredArgsConstructor

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //tag::SecurityFilterChain[]
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            //tag::https[]
            .requiresChannel(channel -> channel
                .anyRequest().requiresSecure()
            )
            //end::https[]
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("", "/", "/registration/create", "/login").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                    .loginPage("/login")
                    .loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error=true")
                    .permitAll()
            )
            .logout((logout) -> logout
                    .logoutUrl("/perform_logout")
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/")
                    .permitAll()
            )
            //tag::oauth2Login[]
            .oauth2Login(oauth2 -> oauth2
                    .loginPage("/login")
                    .authorizationEndpoint(authorization -> authorization
                            .baseUri("/login/oauth2/authorization")
                    )
                    .redirectionEndpoint(redirection -> redirection
                            .baseUri("/login/oauth2/code/google")
                    )
            );
            //end::oauth2Login[]
        return http.build();
    }
    //end::SecurityFilterChain[]

    //tag::passwordEncoder[]
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //end::passwordEncoder[]
}
