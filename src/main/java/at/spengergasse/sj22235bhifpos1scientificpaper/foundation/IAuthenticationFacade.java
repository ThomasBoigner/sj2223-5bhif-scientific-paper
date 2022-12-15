package at.spengergasse.sj22235bhifpos1scientificpaper.foundation;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade{
    Authentication getAuthentication();
}
