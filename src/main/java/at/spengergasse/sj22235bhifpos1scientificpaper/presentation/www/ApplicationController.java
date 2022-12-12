package at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.Message;
import at.spengergasse.sj22235bhifpos1scientificpaper.domain.User;
import at.spengergasse.sj22235bhifpos1scientificpaper.service.MessageService;
import at.spengergasse.sj22235bhifpos1scientificpaper.service.UserService;
import at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www.error.UserAlreadyExistException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping(ApplicationController.BASE_URL)
public class ApplicationController implements RedirectForwardSupport{

    public static final String BASE_URL = "";
    public static final String PATH_VAR_ID = "/{id}";
    public static final String ROUTE_INDEX = "/";
    public static final String ROUTE_SHOW = "/show" + PATH_VAR_ID;
    public static final String ROUTE_NEW = "/new";
    public static final String ROUTE_EDIT = "/edit" + PATH_VAR_ID;
    public static final String ROUTE_DELETE = "/delete" + PATH_VAR_ID;
    public static final String ROUTE_REGISTRATION = "/registration";


    private final UserService userService;
    private final MessageService messageService;

    @ModelAttribute
    public void dateTimeFormat(Model model){
        model.addAttribute("dateTimeFormat", "dd-MM-yyyy HH:mm");
    }

    @GetMapping({"", ROUTE_INDEX})
    public String showDashboard(Model model){
        List<Message> messages = messageService.getMessages();
        model.addAttribute("messages", messages);
        return "dashboard";
    }

    @GetMapping(ApplicationController.ROUTE_REGISTRATION)
    public String showRegistrationForm(Model model){
        model.addAttribute("form", new CreateUserForm());
        return "registration";
    }

    @PostMapping(ApplicationController.ROUTE_REGISTRATION)
    public String registerUserAccount(@ModelAttribute("user")@Valid CreateUserForm form, HttpServletRequest request, Errors errors, Model model){
        try{
            User registered = userService.registerNewUserAccount(form);
        }catch (UserAlreadyExistException uaeEx){
            model.addAttribute("form", form);
            model.addAttribute("message", "An account for that username/email already exists.");
            return "registration";
        }
        return redirect(ROUTE_INDEX);
    }
}
