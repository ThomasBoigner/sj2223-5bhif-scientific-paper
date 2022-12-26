package at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www;

import at.spengergasse.sj22235bhifpos1scientificpaper.validation.PasswordMatches;
import at.spengergasse.sj22235bhifpos1scientificpaper.validation.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@PasswordMatches
public class CreateUserForm {

    @NotBlank
    private String Username;

    @NotBlank
    private String password;
    private String matchingPassword;

    @NotBlank
    @ValidEmail
    private String email;
}
