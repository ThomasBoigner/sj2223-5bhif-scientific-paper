package at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class NewMessageForm {
    @NotBlank
    private String message;
}
