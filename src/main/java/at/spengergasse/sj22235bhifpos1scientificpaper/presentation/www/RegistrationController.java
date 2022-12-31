package at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.User;
import at.spengergasse.sj22235bhifpos1scientificpaper.foundation.IAuthenticationFacade;
import at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www.error.UserAlreadyExistException;
import at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www.forms.CreateUserForm;
import at.spengergasse.sj22235bhifpos1scientificpaper.service.MessageService;
import at.spengergasse.sj22235bhifpos1scientificpaper.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor

@Controller
@RequestMapping(RegistrationController.BASE_URL)
public class RegistrationController implements RedirectForwardSupport {
    private final UserService userService;
    private final MessageService messageService;
    private final IAuthenticationFacade authenticationFacade;

    public static final String BASE_URL = "/registration";
    public static final String PATH_VAR_ID = "/{id}";
    public static final String ROUTE_INDEX = "/";
    public static final String ROUTE_LOGIN = "/login";
    public static final String ROUTE_REGISTRATION = "/create";

    //tag::RegistrationController[]
    @GetMapping(ROUTE_REGISTRATION)
    public String showRegistrationForm(Model model){
        model.addAttribute("form", new CreateUserForm());
        return "registration";
    }

    @PostMapping(ROUTE_REGISTRATION)
    public String registerUserAccount(@ModelAttribute("user")@Valid CreateUserForm form, BindingResult brNewUserForm, HttpServletRequest request, Errors errors, Model model){
        if (brNewUserForm.hasErrors()){
            model.addAttribute("form", form);
            return "registration";
        }
        try{
            User registered = userService.registerNewUserAccount(form);
        }catch (UserAlreadyExistException uaeEx){
            model.addAttribute("form", form);
            model.addAttribute("message", "An account for that username/email already exists.");
            return "registration";
        }
        return redirect(ROUTE_INDEX);
    }
    //end::RegistrationController[]
}

