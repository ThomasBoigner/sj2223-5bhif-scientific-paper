package at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www;

import at.spengergasse.sj22235bhifpos1scientificpaper.validation.PasswordMatches;
import at.spengergasse.sj22235bhifpos1scientificpaper.validation.ValidEmail;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
