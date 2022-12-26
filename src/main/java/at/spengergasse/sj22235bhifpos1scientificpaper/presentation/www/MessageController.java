package at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www;

import at.spengergasse.sj22235bhifpos1scientificpaper.domain.Message;
import at.spengergasse.sj22235bhifpos1scientificpaper.domain.User;
import at.spengergasse.sj22235bhifpos1scientificpaper.foundation.IAuthenticationFacade;
import at.spengergasse.sj22235bhifpos1scientificpaper.service.MessageService;
import at.spengergasse.sj22235bhifpos1scientificpaper.service.UserService;
import at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www.error.UserAlreadyExistException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@Controller
@RequestMapping(MessageController.BASE_URL)
public class MessageController implements RedirectForwardSupport{

    private final UserService userService;
    private final MessageService messageService;
    private final IAuthenticationFacade authenticationFacade;

    public static final String BASE_URL = "";
    public static final String ROUTE_INDEX = "/";
    public static final String ROUTE_CREATE = "/message/create";

    @ModelAttribute
    public void dateTimeFormat(Model model){
        model.addAttribute("dateTimeFormat", "dd-MM-yyyy HH:mm");
    }

    @GetMapping({"", ROUTE_INDEX})
    public String showDashboard(Model model){
        List<Message> messages = messageService.getMessages();
        model.addAttribute("messages", messages);
        model.addAttribute("currentUser", authenticationFacade.getAuthentication().getName());
        return "dashboard";
    }

    @GetMapping(ROUTE_CREATE)
    public String showCreateForm(Model model){
       model.addAttribute("messageForm", new NewMessageForm());
       return "message/newMessage";
    }

    @PostMapping(ROUTE_CREATE)
    public String createMessage(@Valid NewMessageForm messageForm, BindingResult br, Model model){
       return redirect(BASE_URL);
    }
}
