package at.spengergasse.sj22235bhifpos1scientificpaper.validation;

import at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www.CreateUserForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation){}
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        CreateUserForm form = (CreateUserForm) obj;
        return form.getPassword().equals(form.getMatchingPassword());
    }
}
