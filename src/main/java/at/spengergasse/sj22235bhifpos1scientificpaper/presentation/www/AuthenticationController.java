package at.spengergasse.sj22235bhifpos1scientificpaper.presentation.www;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor

@Controller
@RequestMapping(AuthenticationController.BASE_URL)
public class AuthenticationController {

    public static final String BASE_URL = "/login";
    public static final String ROUTE_INDEX = "/";


    @GetMapping({"", ROUTE_INDEX})
    public String showLogin(){
        return "login";
    }

}
